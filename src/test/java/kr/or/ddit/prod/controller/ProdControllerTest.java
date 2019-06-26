package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.ControllerTestEnv;

public class ProdControllerTest extends ControllerTestEnv{

	/**
	* Method : prodListTest
	* 작성자 : PC19
	* 변경이력 :
	* @throws Exception
	* Method 설명 : Prod 페이징 처리 테스트
	*/
	@Test
	public void prodListTest() throws Exception {
		/***Given***/
		

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prod/prod")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		List<ProdVO> list = (List<ProdVO>) mav.getModelMap().get("pageList");
		PageVO pageVO = (PageVO) mav.getModelMap().get("pageVo");
		int paginationSize = (int) mav.getModelMap().get("pagenation");

		/***Then***/
		assertEquals(10, list.size());
		assertEquals(new PageVO(1, 10), pageVO);
		assertEquals(8, paginationSize);
	}
}
