package kr.or.ddit.prod.dao;

import java.util.List;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdDao {
	/**
	* Method : getPageProd
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 :prod 리스트를 페이징 처리해서 조회
	*/
	List<ProdVO> getPageProd(PageVO pageVO);
	
	/**
	* Method : getProdCnt
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : prod 리스트 수를 조회
	*/
	int getProdCnt();
}
