package kr.or.ddit.ajax.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	@Resource(name="userServiceImpl")
	private IuserService service;
	
	/**
	* Method : view
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : ajax 기능 호출용 view
	*/
	@RequestMapping("/view")
	public String view() {
		
		return "tiles.ajax";
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/requestData")
	public String requestData(Model model) {
		
		model.addAttribute("pageVO", new PageVO(5, 10));
//		model.addAttribute("pageVO2", new PageVO(2, 10));
		
//		List<String> rangers = new ArrayList<String>();
//		rangers.add("brown");
//		rangers.add("cony");
//		rangers.add("sally");
//		rangers.add("james");
//		model.addAttribute("rangers", rangers);
		
		/*
		 * {pageVO : {page : 5, pageSize : 10}
		 * {pageVO : {page : 5, pageSize : 10}, pageVO2 : {page : 2, pageSize : 10}
		 * {pageVO : {page : 5, pageSize : 10}, pageVO2 : {page : 2, pageSize : 10}, rangers:{"brown", "cony", "sally", "james"}
		 */
		
		return "jsonView";
	}
	
	@RequestMapping("/requestDataResponseBody")
	@ResponseBody // ==> 응답 내용을 responseBody에 작성해라
	public PageVO requestDataResponseBody() {
		
		return new PageVO(5, 10);
	}
	
	
	
	
	
	
	@RequestMapping("/user")
	public String userList(String userId, Model model) {
		UserVO userVO = service.getUser(userId);
		
		model.addAttribute("userVO", userVO);
		
		return "jsonView";
	}
	
	
	@RequestMapping("/userHtml")
	public String userHtml(String userId, Model model) {
		
		UserVO userVO = service.getUser(userId);
		
		model.addAttribute("userVO", userVO);
		
		return "/user/userHtml";
	}
	
	
	
	@RequestMapping(path = "/requestBody", method = RequestMethod.POST,
					consumes = {"application/json"},	// consumes : content-type 제한
					produces = {"application/json", "application/xml"})	// produces : 메서드가 생성 가능한 타입(accept 헤더를 보고 판단)
	@ResponseBody
	public UserVO request(@RequestBody UserVO userVO) {
		logger.debug("userVO : {}", userVO);
		userVO.setUserId(userVO.getUserId() + "_MODIFY");
		userVO.setPass(userVO.getPass() + "_MODIFY");
		
		return userVO;
	}
}
