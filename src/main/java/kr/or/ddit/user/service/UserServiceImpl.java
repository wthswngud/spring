package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
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
		return dao.insertUser(userVo);
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
		return dao.deleteUser(string);
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
		return dao.getUser(userId);
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
		return dao.modiUser(userVo);
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
		return dao.usersCount();
	}

	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 전체 리스트의 페이징 처리
	*/
	@Override
	public Map<String, Object> getPaging(PageVO pageVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserVO> userList = dao.getPaging(pageVO);
		//usersCnt --> paginationSize 변경
		int usersCnt = dao.usersCount();
		
		//pageSize --> pageVo.getPageSize();
		int paginationSize = (int)Math.ceil((double)usersCnt/pageVO.getPageSize());
		
		map.put("userList", userList);
		map.put("paginationSize", paginationSize);
		
		return map;
	}

	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 리스트
	*/
	@Override
	public List<UserVO> userListForPassEncrypt(SqlSession sqlSession) {
		return dao.userListForPassEncrypt(sqlSession);
	}

	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC19
	* 변경이력 :
	* @param sqlSession
	* @param userVO
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 적용
	*/
	@Override
	public int updateUserEncryptPass(SqlSession sqlSession, UserVO userVO) {
		return dao.updateUserEncryptPass(sqlSession, userVO);
	}
}
