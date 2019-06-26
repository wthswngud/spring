package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdDaoImplTest extends LogicTestEnv{
	@Resource(name="prodDaoImpl")
	private IProdDao dao;

	@Test
	public void getPageProdTest() {
		/***Given***/
		
		/***When***/
		List<ProdVO> list =  dao.getPageProd(new PageVO(1, 10));

		/***Then***/
		assertNotNull(list);
		assertEquals(10, list.size());
	}
	
	@Test
	public void getProdCnt() {
		/***Given***/
		

		/***When***/
		int cnt = dao.getProdCnt();
		/***Then***/
		assertTrue(cnt>0);
	}
}
