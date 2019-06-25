package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv {
	@Resource(name = "userServiceImpl")
	private IuserService service;

	/**
	 * Method : userListTest 작성자 : PC19 변경이력 : Method 설명 : 전체 사용자 조회
	 */
	@Test
	public void userListTest() {
		/*** Given ***/

		/*** When ***/
		List<UserVO> userlist = service.userList();
		/*** Then ***/
		assertNotNull(userlist);
	}

	/**
	 * Method : insertUserTest 작성자 : PC19 변경이력 :
	 * 
	 * @throws ParseException Method 설명 : 사용자 정보 등록 테스트
	 */
	@Test
	public void insertUserTest() throws ParseException {
		/*** Given ***/
		// 사용자 정보를 담고 있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserVO userVo = new UserVO("userTest", "대덕인", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩", "", "", "34940",
				sdf.parse("2019-05-31"));

		/*** When ***/
		int insertCnt = service.insertUser(userVo);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}

	/**
	 * Method : deleteUserTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 정보 삭제 테스트
	 */
	@Test
	public void deleteUserTest() {
		/*** Given ***/

		/*** When ***/
		int result = service.deleteUser("user39");

		/*** Then ***/
		assertEquals(1, result);
	}

	/**
	 * Method : getUserTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 정보 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/*** Given ***/
		String userId = "brown";
		/*** When ***/
		UserVO userVO = service.getUser(userId);
		/*** Then ***/
		assertEquals("브라운", userVO.getName());
		assertEquals("곰", userVO.getAlias());
	}

	/**
	 * Method : modiUserTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 정보 수정 테스트
	 */
	@Test
	public void modiUserTest() throws ParseException {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserVO userVo = new UserVO("user39", "대덕인", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩", "", "", "34940",
				sdf.parse("2019-05-31"));

		/*** When ***/
		int result = service.modiUser(userVo);

		/*** Then ***/
		assertEquals(1, result);
	}

	/**
	 * Method : usersCntTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 전체 수 조회 테스트
	 */
	@Test
	public void usersCntTest() {
		/*** Given ***/

		/*** When ***/
		int usersCnt = service.usersCount();

		/*** Then ***/
		assertEquals(105, usersCnt);
	}

	/**
	* Method : getPaging
	* 작성자 : PC19
	* 변경이력 :
	* @param pageVO
	* @return
	* Method 설명 : 전체 리스트의 페이징 처리
	*/
	@Test
	public void getPaging() {
		/*** Given ***/
		PageVO pageVO = new PageVO(1, 10);

		/*** When ***/
		Map<String, Object> map = service.getPaging(pageVO);
		

		List<UserVO> userList = (List<UserVO>) map.get("userList");
		int paginationSize = (int) map.get("paginationSize");

		/*** Then ***/
		assertNotNull(map);
		assertEquals(10, userList.size());
		assertEquals(11, paginationSize);
	}
	
//	@Test
//	public void userListForPassEncrypt() {
//		/***Given***/
//		
//
//		/***When***/
//
//		/***Then***/
//	}
}
