package kr.or.ddit.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

@WebAppConfiguration()
public class LoginControllerTest extends ControllerTestEnv{

	/**
	* Method : loginViewNotLoginTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 접속하지 않은상황에서 loginView 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginViewNotLoginTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("login/login", viewName);
	}
	
	/**
	* Method : loginViewLoginTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인한 상황에서 로그인 뷰 요청 테스트
	*/
	@Test
	public void loginViewLoginTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/login").sessionAttr("USER_INFO", new UserVO())).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("main", viewName);
	}
	
	/**
	* Method : loginProcessSuccessTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 로그인 요청 처리 성공 테스트
	 * @throws Exception 
	*/
	@Test
	public void loginProcessSuccessTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown1234";	//userId + 1234
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login").param("userId", userId).param("password", password)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		HttpSession session = mvcResult.getRequest().getSession();

		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		String viewName = mav.getViewName();
		
		/***Then***/
//		assertNotNull(mvcResult);
		assertEquals("main", viewName);
		assertEquals("브라운", userVO.getName());
	}
	
	/**
	* Method : loginProcessFailTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 요청 실패
	*/
	@Test
	public void loginProcessFailTest() throws Exception {
		/***Given***/
		String userId = "brown";
		String password = "brown5678";	// 틀린 비밀번호
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/login")
									 .param("userId", userId)
									 .param("password", password))
									 .andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();

		String viewName = mav.getViewName();
		
		/***Then***/
//		assertNotNull(mvcResult);
		assertEquals("login/login", viewName);
	}
}
