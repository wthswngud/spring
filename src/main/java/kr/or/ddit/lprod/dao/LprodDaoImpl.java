package kr.or.ddit.lprod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodDaoImpl implements IlprodDao{
	
	@Resource(name="sqlSession")
	private SqlSession session;

	/**
	* Method : getLprod
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 처리된 리스트 조회
	*/
	@Override
	public List<LprodVO> getPageLprod(PageVO pageVO) {
		return session.selectList("lprod.getPageLprod", pageVO);
	}

	/**
	* Method : getLprodCnt
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 리스트의 수 조회
	*/
	@Override
	public int getLprodCnt() {
		return session.selectOne("lprod.getLprodCnt");
	}
}