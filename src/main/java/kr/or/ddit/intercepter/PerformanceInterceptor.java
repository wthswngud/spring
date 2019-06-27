package kr.or.ddit.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PerformanceInterceptor extends HandlerInterceptorAdapter{
	private static final Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// true(다음 인터셉터 혹은 인터셉터가 없을경우 controller)
		// false 요청 중단
		logger.debug("preHandle : {}", request.getRequestURI());
		request.setAttribute("start", System.currentTimeMillis());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		logger.debug("postHandle : {}", request.getRequestURI());
		
		long end = System.currentTimeMillis();
		
		long result = end - (long)request.getAttribute("start");
		
		logger.debug("resultTime : {}", result);
		
		super.postHandle(request, response, handler, modelAndView);
	}

}
