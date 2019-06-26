package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdDaoImpl implements IProdDao{
	@Resource(name="sqlSession")
	SqlSession sqlSession;

	/**
	* Method : getPageProd
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 :prod 리스트를 페이징 처리해서 조회
	*/
	@Override
	public List<ProdVO> getPageProd(PageVO pageVO) {
		return sqlSession.selectList("prod.getPageProd",pageVO);
	}

	/**
	* Method : getProdCnt
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : prod 리스트 수를 조회
	*/
	@Override
	public int getProdCnt() {
		return sqlSession.selectOne("prod.getProdCnt");
	}
}
