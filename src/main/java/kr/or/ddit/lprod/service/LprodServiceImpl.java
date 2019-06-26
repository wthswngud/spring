package kr.or.ddit.lprod.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.dao.IlprodDao;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.paging.model.PageVO;

@Repository
public class LprodServiceImpl implements IlprodService{
	@Resource(name="lprodDaoImpl")
	private IlprodDao dao;

	/**
	* Method : getLprod
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 처리된 리스트 조회
	*/
	@Override
	public List<LprodVO> getPageLprod(PageVO pageVO) {
		return dao.getPageLprod(pageVO);
	}
}
