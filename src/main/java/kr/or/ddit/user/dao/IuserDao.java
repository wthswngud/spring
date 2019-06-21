package kr.or.ddit.user.dao;

import java.util.List;

import kr.or.ddit.user.model.UserVO;

public interface IuserDao {
	
	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	List<UserVO> userList();

	UserVO getUser(String userId);

	int getUser(UserVO userVo);

	int deleteUser(String string);

	int modiUser(UserVO userVo);

	int usersCount();
	
	int insertUser(UserVO userVo);
}
