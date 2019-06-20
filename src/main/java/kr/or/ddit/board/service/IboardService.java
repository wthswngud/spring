package kr.or.ddit.board.service;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IboardDao;


public interface IboardService {
	String sayHello();
	
	IboardDao getBoardDao();
}
