package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import kr.or.ddit.user.dao.IuserDao;
import kr.or.ddit.user.model.UserVO;

@Repository
public class UserServiceImpl implements IuserService{
	@Resource(name="userDaoImpl")
	private IuserDao dao;

	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	@Override
	public List<UserVO> userList() {
		return dao.userList();
	}

	@Override
	public int insertUser(UserVO userVo) {
		return dao.insertUser(userVo);
	}

	@Override
	public int deleteUser(String string) {
		return dao.deleteUser(string);
	}
}
