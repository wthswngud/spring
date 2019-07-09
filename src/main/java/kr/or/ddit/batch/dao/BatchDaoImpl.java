package kr.or.ddit.batch.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.batch.model.BatchVO;

@Repository
public class BatchDaoImpl implements IBatchDao{
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : deleteDaily
	* 작성자 : PC19
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 삭제
	*/
	@Override
	public int deleteDaily(String ym) {
		int result = sqlSession.delete("batch.deleteDaily", ym);
		return result;
	}

	/**
	* Method : createDaily
	* 작성자 : PC19
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성
	*/
	@Override
	public int createDaily(String ym) {
		int result = sqlSession.insert("batch.createDaily", ym);
		return result;
	}

	/**
	* Method : insertBatch
	* 작성자 : PC19
	* 변경이력 :
	* @param batchVO
	* @return
	* Method 설명 : 배치 실행 데이터 생성 테스트
	*/
	@Override
	public int insertBatch(BatchVO batchVO) {
		return sqlSession.insert("batch.insertBatch", batchVO);
	}

	/**
	* Method : updateBatch
	* 작성자 : PC19
	* 변경이력 :
	* @param batchVO
	* @return
	* Method 설명 : 배치 정보 수정
	*/
	@Override
	public int updateBatch(BatchVO batchVO) {
		return sqlSession.update("batch.updateBatch", batchVO);
	}
}
