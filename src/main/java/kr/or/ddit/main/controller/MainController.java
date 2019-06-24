package kr.or.ddit.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 servlet 
 	- extends : HttpServlet
 	- servlet-mapping(ewb.xml, @WebServlet)
 	- service -> doXXX
 	
 spring
 	- pojo(Plain Old Java Object), @Controller
 	- @RequestMapping(class, method)
 	- @RequestMapping에 설정한 url method 호출
*/

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@RequestMapping("/main")
	public String mainView(HttpServletRequest request, Model model) {
		
		logger.debug("main.view");
		// prefix : /WEB-INF/views/
		// suffix : .jsp
		
		// prefix + viewName + surffix
		// /WEB-INF/views/main.jsp
		
		model.addAttribute("mainUserId", "brown");
		
		//viewName
		return "main";
	}
}
