package kr.or.ddit.login.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IuserService;

@Controller
public class LoginController {

	@Resource(name = "userServiceImpl")
	private IuserService userService;
	
	/**
	* Method : loginView
	* 작성자 : PC19
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 사용자 로그인 화면 요청
	*/
	@RequestMapping(path = "/login", method=RequestMethod.GET)
	public String loginView(HttpSession session) {
		if(session.getAttribute("USER_INFO") != null)
			return "main";			// /WEB-INF/views/main.jsp
		else
			return "login/login";	// /WEB-INF/login/login.jsp
		
	}
	
	/**
	* Method : loginProcess
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 로그인 요청 처리
	*/
	@RequestMapping(path = "/login", method=RequestMethod.POST)
	public String loginProcess(String userId, String password, String rememberme, HttpServletResponse response, HttpSession session) {
		String encryptPassword = KISA_SHA256.encrypt(password);
		
		UserVO userVO = userService.getUser(userId);
		
		if(userVO!=null && encryptPassword.equals(userVO.getPass())) {
			rememberCookie(userId, rememberme, response);
			
			session.setAttribute("USER_INFO", userVO);
			return "main";
		}else
			return "login/login";
	}
	

	
	/**
	* Method : rememberCookie
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @param rememberme
	* @param response
	* Method 설명 : rememberme 쿠키를 응답으로 생성
	*/
	private void rememberCookie(String userId, String rememberme, HttpServletResponse response) {
		int cookieMaxAge=0;
		if(rememberme!=null);
		
		cookieMaxAge = 60*60*24*30;
		
		Cookie userIdCookie = new Cookie("userId", userId);
		userIdCookie.setMaxAge(cookieMaxAge);
		
		Cookie rememberMeCookie = new Cookie("rememberme", "true");
		rememberMeCookie.setMaxAge(cookieMaxAge);
		
		response.addCookie(rememberMeCookie);
		response.addCookie(userIdCookie);
	}
}
