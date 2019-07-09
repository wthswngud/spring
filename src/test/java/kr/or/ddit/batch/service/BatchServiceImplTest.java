package kr.or.ddit.batch.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;

public class BatchServiceImplTest extends LogicTestEnv{
	@Resource(name="batchServiceImpl")
	private IBatchService service;

	/**
	* Method : createDaily
	* 작성자 : PC19
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 일실적 일괄 생성
	*/
	@Test
	public void createDailyTest() {
		/***Given***/
		String ym = "201907";
		

		/***When***/
		int result = service.createDaily(ym);

		/***Then***/
		assertEquals(69, result);
	}
}
