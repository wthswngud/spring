package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

public interface IlprodService {
	/**
	* Method : getLprod
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 처리된 리스트 조회
	*/
	List<LprodVO> getPageLprod(PageVO pageVO);
}
