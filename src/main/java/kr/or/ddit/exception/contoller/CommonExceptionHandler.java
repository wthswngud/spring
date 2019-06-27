package kr.or.ddit.exception.contoller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public String handleException() {
		return "exception";
	}
	
	
}
