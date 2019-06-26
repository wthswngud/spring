package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.ProdVO;

@Repository
public class ProdServiceImpl implements IProdService{
	@Resource(name="prodDaoImpl")
	private IProdDao dao;

	@Override
	public Map<String, Object> getPaging(PageVO pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ProdVO> list = dao.getPageProd(pageVO);
		int prodCnt = dao.getProdCnt();
		
		int paginationSize = (int)Math.ceil((double)prodCnt/pageVO.getPageSize());
		
		map.put("list", list);
		map.put("paginationSize", paginationSize);
		return map;
	}
}
