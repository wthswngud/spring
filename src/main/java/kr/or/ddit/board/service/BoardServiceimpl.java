package kr.or.ddit.board.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IboardDao;

@Service
public class BoardServiceimpl implements IboardService{
	//property or filed
	@Resource(name="boardDaoImpl")
	private IboardDao boardDao;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BoardServiceimpl(IboardDao boardDao) {
		this.boardDao = boardDao;
	}

	public BoardServiceimpl() {
		
	}

	public IboardDao getBoardDao() {
		return boardDao;
	}
	
	public void setBoardDao(IboardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	@Override
	public String sayHello() {
		return boardDao.sayHello();
	}
}
