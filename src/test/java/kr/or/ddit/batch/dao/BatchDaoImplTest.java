package kr.or.ddit.batch.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.batch.model.BatchVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class BatchDaoImplTest extends LogicTestEnv{
	private static final Logger logger = LoggerFactory.getLogger(BatchDaoImplTest.class);
	
	@Resource(name="batchDaoImpl")
	private IBatchDao dao;

	/**
	* Method : deleteDaily
	* 작성자 : PC19
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 일실적 일괄 생성 테스트
	*/
	@Test
	public void createDailyTest() {
		/***Given***/
		String ym = "201907";

		/***When***/
		int result = dao.createDaily(ym);

		/***Then***/
		assertEquals(69, result);
	}
	
	/**
	* Method : deleteDailyTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 일실적 일괄 삭제
	*/
	@Test
	public void deleteDailyTest() {
		/***Given***/
		String ym = "201907";
		dao.createDaily(ym);

		/***When***/
		int result = dao.deleteDaily(ym);

		/***Then***/
		assertEquals(69, result);
	}
	
	/**
	* Method : insertBatchTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 배치 실행 데이터 생성 테스트
	*/
	@Test
	public void insertBatchTest() {
		/***Given***/
		BatchVO batchVO = new BatchVO();
		batchVO.setBcd("01");	//일실적 배치 : 01
		batchVO.setSt("01");	//배치 실행상태 : 01 - 진행중

		/***When***/
		logger.debug("before insertBatch batchVO.getBid() : {}", batchVO.getBid());
		int result = dao.insertBatch(batchVO);
		logger.debug("after insertBatch batchVO.getBid() : {}", batchVO.getBid());

		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : updateBatch
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 배치데이터 업데이트 테스트
	*/
	@Test
	public void updateBatch() {
		/***Given***/
		BatchVO batchVO = new BatchVO();
		batchVO.setBcd("01");	//일실적 배치 : 01
		batchVO.setSt("01");	//배치 실행상태 : 01 - 진행중
		
		dao.insertBatch(batchVO);	//bid가 들어가 있는 상태(sequence 값)
		logger.debug("bid : {}", batchVO.getBid());
		
		batchVO.setSt("02");	//배치 실행상태 : 01 - 진행중

		/***When***/
		int result = dao.updateBatch(batchVO);

		/***Then***/
		assertEquals(1, result);
	}
}
