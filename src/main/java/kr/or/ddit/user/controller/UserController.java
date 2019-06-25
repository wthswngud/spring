package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

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
	@RequestMapping("/userList")
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
	
	@RequestMapping("/userPagingList")
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
}
