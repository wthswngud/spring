package kr.or.ddit.lprod.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;

@RunWith(SpringJUnit4ClassRunner.class)
public class LprodDaoImplTest extends LogicTestEnv {
	@Resource(name="lprodDaoImpl")
	private IlprodDao dao;

	/**
	* Method : getLprod
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 처리된 리스트 조회
	* 
	*/
	@Test
	public void getPageLprodTest() {
		/***Given***/

		/***When***/
		List<LprodVO> lprodList = dao.getPageLprod(new PageVO(1, 10));

		/***Then***/
		assertNotNull(lprodList);
	}
	
	/**
	* Method : getLprodCnt
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 리스트의 수 조회
	*/
	@Test
	public void getLprodCntTest() {
		/***Given***/

		/***When***/
		int cnt = dao.getLprodCnt();

		/***Then***/
		assertTrue(cnt>0);
	}
}
