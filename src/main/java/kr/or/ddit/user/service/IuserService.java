package kr.or.ddit.user.service;

import java.util.List;

import kr.or.ddit.user.model.UserVO;

public interface IuserService {
	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 조회
	*/
	List<UserVO> userList();

	int insertUser(UserVO userVo);

	int deleteUser(String string);
	
	UserVO getUser(String userId);
}
