package kr.or.ddit.batch.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.batch.dao.IBatchDao;
import kr.or.ddit.batch.model.BatchVO;

@Service
public class BatchServiceImpl implements IBatchService{
	@Resource(name="batchDaoImpl")
	private IBatchDao dao;
	
	/**
	* Method : createDaily
	* 작성자 : PC19
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 일실적 일괄 생성
	*/
	@Override
	public int createDaily(String ym) {
		//배치 정보 생성
		BatchVO batchVO = new BatchVO();
		batchVO.setBcd("01");	//배치 구분 코드 : 01-일실적 배치
		batchVO.setSt("01");	//배치 상태 코드 : 01-진행중
		dao.insertBatch(batchVO);
		
		dao.deleteDaily(ym);
		
		//배치 정보 상태 수정
		batchVO.setSt("02");	//배치 상태 코드 : 02-정상 완료
		dao.updateBatch(batchVO);
		
		return dao.createDaily(ym);
	}
}
