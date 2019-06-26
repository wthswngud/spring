package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;

public interface IProdService {
	
	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 페이지네이션 수를 조회
	*/
	Map<String, Object> getPaging(PageVO pageVO);
}
