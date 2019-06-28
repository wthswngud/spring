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
		assertEquals("tiles.main", viewName);
		assertEquals("brown", userId);
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVO"));
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
				.andExpect(view().name("tiles.main"))
				.andExpect(model().attribute("mainUserId", "brown"))
				.andExpect(model().attributeExists("rangers"))
				.andExpect(model().attributeExists("userVO"));
		
		
	}
	
	/**
	* Method : mainViewMavTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : ModelAndView 객체를 이용한 main 페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();

		/***Then***/
		//viewName이 기대하는 문자열로 리턴 되는지
		String viewName = mav.getViewName();
		assertEquals("main", viewName);
		
		//model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mav.getModel().get("mainUserId"));
		
		assertNotNull(mav.getModel().get("rangers"));
	}
	
	/**
	* Method : pathvariableTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : @Pathvariable 테스트 
	 * @throws Exception 
	*/
	@Test
	public void pathvariableTest() throws Exception {
		mockMvc.perform(get("/main/pathvariable/brown"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"));
	}
	
	/**
	* Method : pathvariableTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : @Pathvariable 테스트 
	 * @throws Exception 
	*/
	@Test
	public void pathvariableTest2() throws Exception {
		mockMvc.perform(get("/main/pathvariable/sally"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"));
	}
	
	/**
	* Method : requestHeaderTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 : @RequestHeader test
	*/
	@Test
	public void requestHeaderTest() throws Exception {
		mockMvc.perform(get("/main/header").accept("text/html"))
				.andExpect(status().isOk())
				.andExpect(view().name("main"));
	}
}
