package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.prod.model.ProdVO;
import kr.or.ddit.prod.service.IProdService;

@RequestMapping("/prod")
@Controller
public class ProdController {
	@Resource(name = "prodServiceImpl")
	private IProdService service;
	
	
	@RequestMapping("/prod")
	public String prodList(PageVO pageVO, Model model) {
		
		Map<String, Object> map = service.getPaging(pageVO);
		List<ProdVO> list = (List<ProdVO>) map.get("list");
		int paginationSize = (int) map.get("paginationSize");
		
		model.addAttribute("pageList", list);
		model.addAttribute("pageVo", pageVO);
		model.addAttribute("pagenation", paginationSize);
		
		return "user/prod";
	}
}
