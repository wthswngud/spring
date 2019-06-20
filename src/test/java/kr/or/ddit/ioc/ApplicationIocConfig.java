package kr.or.ddit.ioc;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.BoardServiceimpl;
import kr.or.ddit.board.service.IboardService;

@Configuration
public class ApplicationIocConfig {
//	<bean id="boardDao" class="kr.or.ddit.board.dao.BoardDaoImpl"/>
	
	@Bean
	public IboardDao boardDao() {
		return new BoardDaoImpl();
	}
	
	@Resource(name = "boardService")
	private IboardService boardService;
	
	/*
	<bean id="boardService" class="kr.or.ddit.board.service.BoardServiceimpl">
		<property name="boardDao" ref="boardDao"/>
	</bean>
	*/
	
	@Bean
	public BoardServiceimpl boardDservice() {
		BoardServiceimpl boardService = new BoardServiceimpl();
		boardService.setName("");
		
		
		return boardService;
	}
}
