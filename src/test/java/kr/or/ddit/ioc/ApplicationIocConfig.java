package kr.or.ddit.ioc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardServiceimpl;

@Configuration
public class ApplicationIocConfig {
//	<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDaoImpl"/>
	
	@Bean
	public IboardDao boardDaoImpl() {
		return new BoardDaoImpl();
	}
	
	/*
	<bean id="boardService" class="kr.or.ddit.board.service.BoardServiceimpl">
		<property name="boardDao" ref="boardDao"/>
	</bean>
	*/
	
	@Bean
	public BoardServiceimpl boardServiceimpl() {
		BoardServiceimpl boardService = new BoardServiceimpl();
//		boardService.setName("brown");
		boardService.setBoardDao(boardDaoImpl());
		return boardService;
	}
}
