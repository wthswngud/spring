package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userServiceImpl")
	private IuserService userService;
	
	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트
	*/
	//localhost/user/list
	@RequestMapping("/list")
	public String userList(Model model) {
		
		model.addAttribute("userList", userService.userList());
		
		return "user/userList";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC19
	* 변경이력 :
	* @param page
	* @param pageSize
	* @return
	* Method 설명 : 사용자 페이징 리스트
	*/
//	@RequestMapping("/userPagingList")
//	public String userPagingList(@RequestParam(name="page", defaultValue = "1")int page,
//								 @RequestParam(name="pageSize", defaultValue = "10")int pageSize,
//								 Model model){
//		logger.debug("page : {}", page);
//		logger.debug("pageSize : {}", pageSize);
//		
//		PageVO pageVO = new PageVO(page, pageSize);
	
	@RequestMapping("/pagingList")
	public String userPagingList(PageVO pageVO, Model model){
		logger.debug("pageVO : {}", pageVO);
		
		Map<String, Object> resultMap = userService.getPaging(pageVO);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int pagination = (Integer) resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", pagination);
		model.addAttribute("pageVo", pageVO);
		
		return "user/userPagingList";
	}
	
	/**
	* Method : user
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model) {
		
		model.addAttribute("userVO", userService.getUser(userId));
		
		return "user/user";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록화면
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
	// 사용자 등록
	/**
	* Method : userForm
	* 작성자 : PC19
	* 변경이력 :
	* @param userVO
	* @param profile
	* @param model
	* @return
	* @throws IllegalStateException
	* @throws IOException
	* Method 설명 : 사용자 등록
	*/
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVO userVO, MultipartFile profile, Model model) throws IllegalStateException, IOException {
		logger.debug("profile : {}", profile);
		
		String viewName = "";
		
		UserVO dbUser = userService.getUser(userVO.getUserId());
		
		//중복확인
		if(dbUser==null) {
			if(profile.getSize()>0) {
				String fileName = profile.getOriginalFilename();	// 파일 이름 가져오기
				String ext = PartUtil.getExt(fileName);			// 확장자 가져오기
				String uploadPath = PartUtil.getUploadPath();
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(fileName);
				
				profile.transferTo(new File(filePath));
				
			}
			
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			int insertCnt = userService.insertUser(userVO);
			if(insertCnt == 1) 
				viewName = "redirect:/user/pagingList";
			
		}else {
			model.addAttribute("msg", "이미 존재하는 사용자 입니다.");
			viewName = userForm();
		}
		
		return viewName;
	}
	
	/**
	* Method : profile
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @param response
	* @param request
	* @throws IOException
	* Method 설명 : 사용자 사진 응답 생성
	*/
	@RequestMapping("/profile")
	public void profile(String userId, HttpServletResponse response, HttpServletRequest request) throws IOException {
		FileInputStream fis = null;
		
		//사용자 정보(path)를 조회
		UserVO userVO = userService.getUser(userId);
		
		//path정보로 file을 읽어 들여서
		ServletOutputStream sos = response.getOutputStream();
		String filePath = null;
		if(userVO.getPath() != null){
			filePath = userVO.getPath();
			//사용자가 업로드한 파일이 존재하지 않을 경우 : no_image.gif
			
		}else{
			filePath = request.getServletContext().getRealPath("/image/no_image.gif");
			//webapp/image/no_image.gif
		}
		File file = new File(filePath);
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[512];
			
			//response객체에 스트림으로 써준다.
			while(fis.read(buffer, 0, 512)!=-1){
				sos.write(buffer);
			}
			fis.close();
			sos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Method : userModify
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 수정 화면 요청
	*/
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		model.addAttribute("userVO", userService.getUser(userId));
		
		
		return "user/modify";
	}
	
	@RequestMapping(path = "/modify", method=RequestMethod.POST)
	public String userModify(UserVO userVO, MultipartFile profile, HttpSession session, Model model){
		//비밀번호 암호화 , 추후 ajax 요청으로 분리
		//userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		
		if(profile.getSize() > 0) {
			String fileName = profile.getOriginalFilename();
			String ext = PartUtil.getExt(fileName);
			
			String uploadPath = PartUtil.getUploadPath();
			String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
			
			userVO.setPath(filePath);
			userVO.setFilename(fileName);
			
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		
		int updateCnt = userService.modiUser(userVO);
		
		if(updateCnt == 1) {
			session.setAttribute("msg", "수정 되었습니다.");
			return "redirect:/user?userId="+userVO.getUserId();
		}else {
			return userModify(userVO.getUserId(), model);
		}
	}
}
