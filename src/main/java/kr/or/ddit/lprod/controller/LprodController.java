package kr.or.ddit.lprod.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.service.IlprodService;
import kr.or.ddit.paging.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class LprodController {
	private static final Logger logger = LoggerFactory.getLogger(LprodController.class);
	@Resource
	private IlprodService lprodService;
	
	/**
	* Method : lprodList
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @param model
	* @return
	* Method 설명 : lprod 리스트 반환
	*/
	@RequestMapping("/lprod")
	public String lprodList(PageVO pageVO, Model model) {
		logger.debug("lprodList ---");
		
		List<LprodVO> lprodList = lprodService.getPageLprod(pageVO);
		
		model.addAttribute("pageList", lprodList);
		
		return "user/lprod";
	}
}
