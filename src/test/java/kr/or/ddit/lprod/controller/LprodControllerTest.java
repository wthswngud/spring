package kr.or.ddit.lprod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class LprodControllerTest extends ControllerTestEnv{

	/**
	* Method : lprodListTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 : lprod 페이징 처리 테스트
	*/
	@Test
	public void lprodListTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/lprod")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		List<LprodVO> list = (List<LprodVO>) mav.getModelMap().get("pageList");
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/lprod", viewName);
		assertEquals(9, list.size());
	}
}
