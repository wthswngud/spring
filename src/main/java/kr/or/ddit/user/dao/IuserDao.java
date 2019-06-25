package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.paging.model.PageVO;
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

	/**
	* Method : getUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVO getUser(String userId);

	/**
	* Method : deleteUser
	* 작성자 : PC19
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 : 사용자 정보 삭제
	*/
	int deleteUser(String string);

	/**
	* Method : modiUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int modiUser(UserVO userVo);

	/**
	* Method : usersCount
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 전체 사용자 수 조회
	*/
	int usersCount();
	
	/**
	* Method : insertUser
	* 작성자 : PC19
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVO userVo);

	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 해당하는 페이지 리스트를 반환하는 메서드
	*/
	List<UserVO> getPaging(PageVO pageVO);
	
	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 리스트
	*/
	List<UserVO> userListForPassEncrypt(SqlSession sqlSession);

	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @param userVO
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO);
}
