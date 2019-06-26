package kr.or.ddit.lprod.dao;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface IlprodDao {
	/**
	* Method : getLprod
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 처리된 리스트 조회
	*/
	List<LprodVO> getPageLprod(PageVO pageVO);
	
	/**
	* Method : getLprodCnt
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 리스트의 수 조회
	*/
	int getLprodCnt();
}
