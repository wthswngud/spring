package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVOValidator;
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
	
	@RequestMapping("userListExcel")
	public String userListExcel(String filename, Model model) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		model.addAttribute("filename", filename);
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
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
		
		return "tiles.userPagingList";
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
		logger.debug("userId 보내기 후 : {}", userId);
		
		model.addAttribute("userVO", userService.getUser(userId));
		
		return "user/user";
	}
	
	/**
	* Method : userJson
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model) {
		
		model.addAttribute("userVO", userService.getUser(userId));
		
		return "jsonView";
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
//	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userForm(UserVO userVO, BindingResult result, MultipartFile profile, Model model) throws IllegalStateException, IOException {
		new UserVOValidator().validate(userVO, result);
		
		if(result.hasErrors())
			return "user/userForm";
		
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
		public String userFormJsr(@Valid UserVO userVO, BindingResult result, MultipartFile profile, Model model) throws IllegalStateException, IOException {
			new UserVOValidator().validate(userVO, result);
			
			if(result.hasErrors())
				return "user/userForm";
			
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
	public String profile(String userId, Model model) throws IOException {
		
		UserVO userVO = userService.getUser(userId);
		
		model.addAttribute("userVO", userVO);
		
		return "profileView";
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
	public String userModify(UserVO userVO, MultipartFile profile, HttpSession session, Model model
							 , RedirectAttributes redirectAttributes){
		//비밀번호 암호화 , 추후 ajax 요청으로 분리
		//userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
		logger.debug("userId 보내기 전 : {}", userVO.getUserId());
		
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
			redirectAttributes.addFlashAttribute("msg", "수정 되었습니다.");
//			redirectAttributes.addAttribute("userId", userVO.getUserId());	//파라미터를 자동으로 붙여준다.
			redirectAttributes.addAttribute("userId", userVO.getUserId());	//파라미터를 자동으로 붙여준다.
			return "redirect:/user/user?";
		}else {
			return userModify(userVO.getUserId(), model);
		}
	}
}
