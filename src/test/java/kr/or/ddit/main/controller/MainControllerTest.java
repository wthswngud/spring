package kr.or.ddit.main.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;



//일반 자바 환경 -> 웹환경
//applicationContext -->웹 환경의 applicationContext 생성
@WebAppConfiguration()
public class MainControllerTest extends ControllerTestEnv{
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);

	/**
	* Method : mainViewTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String userId = (String) mav.getModel().get("mainUserId");
		
		
		/***Then***/
		assertNotNull(mvcResult);
		assertEquals("main", viewName);
		assertEquals("brown", userId);
	}
	
	/**
	* Method : mainViewAndExpectTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 :
	*/
	@Test
	public void mainViewAndExpectTest() throws Exception {
		mockMvc.perform(get("/main"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"))
				.andExpect(model().attribute("mainUserId", "brown"));
	}
}
