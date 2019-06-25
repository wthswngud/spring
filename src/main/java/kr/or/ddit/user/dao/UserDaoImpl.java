package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
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

	/**
	* Method : getUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String userId) {
		return sqlSession.selectOne("user.getUser", userId);
	}

	/**
	* Method : deleteUser
	* 작성자 : PC19
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 : 사용자 정보 삭제
	*/
	@Override
	public int deleteUser(String string) {
		return sqlSession.delete("user.deleteUser", string);
	}

	/**
	* Method : modiUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	@Override
	public int modiUser(UserVO userVo) {
		return sqlSession.update("user.modiUser", userVo);
	}

	/**
	* Method : usersCount
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 수 조회
	*/
	@Override
	public int usersCount() {
		return sqlSession.selectOne("user.usersCount");
	}

	/**
	* Method : insertUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}

	@Override
	public List<UserVO> getPaging(PageVO pageVO) {
		return sqlSession.selectList("user.userPagingList", pageVO);
	}

	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return sqlSession.update("user.updateUserEncryptPass", userVO);
	}
}
