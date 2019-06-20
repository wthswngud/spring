package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements IboardDao{
	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}
}
