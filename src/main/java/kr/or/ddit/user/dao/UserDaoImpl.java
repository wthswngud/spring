package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDaoImpl implements IuserDao{
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : userList
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	*/
	@Override
	public List<UserVO> userList() {
		return sqlSession.selectList("user.userList");
	}

	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

	@Override
	public int getUser(UserVO userVo) {
		return sqlSession.selectOne("user.getUser", userVo);
	}

	@Override
	public int deleteUser(String string) {
		return sqlSession.delete("user.deleteUser", string);
	}

	@Override
	public int modiUser(UserVO userVo) {
		return sqlSession.update("user.modiUser", userVo);
	}

	@Override
	public int usersCount() {
		return sqlSession.selectOne("user.usersCount");
	}

	@Override
	public int insertUser(UserVO userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}
}
