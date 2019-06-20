package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.IboardDao;

public class BoardServiceimpl implements IboardService{
	private IboardDao boardDao;
	
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
