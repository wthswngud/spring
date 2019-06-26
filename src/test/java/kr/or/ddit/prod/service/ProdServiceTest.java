package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class ProdServiceTest extends LogicTestEnv{
	@Resource(name="prodServiceImpl")
	IProdService service;

	@Test
	public void getPagingTest() {
		/***Given***/

		/***When***/
		Map<String, Object> map = service.getPaging(new PageVO(1, 10));
		List<ProdVO> list = (List<ProdVO>) map.get("list");
		int pagination = (int) map.get("paginationSize");

		/***Then***/
		assertEquals(10, list.size());
		assertEquals(8, pagination);
	}
}
