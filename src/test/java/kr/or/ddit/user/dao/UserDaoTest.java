package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest extends LogicTestEnv {
	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Resource(name = "userDaoImpl")
	private IuserDao dao;

	/**
	 * Method : userListTest 작성자 : PC19 변경이력 : Method 설명 :사용자 전체 리스트 조회 테스트
	 */
	@Test
	public void userListTest1() {
		/*** Given ***/

		/*** When ***/
		List<UserVO> userlist = dao.userList();
		/*** Then ***/
		assertNotNull(userlist);
//		assertTrue(condition);
	}

	/**
	 * Method : userListTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 전체 조회 테스트
	 */
	@Test
	public void userListTest() {
		/*** Given ***/

		/*** When ***/
		List<UserVO> userList = dao.userList();

		/*** Then ***/
		assertEquals("user39", userList.get(0).getUserId());
		assertEquals(105, userList.size());
//		logger.debug("userList : {}", userList);
	}

	/**
	 * Method : getUserTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 조회 테스트
	 */
	@Test
	public void getUserTest() {
		/*** Given ***/
		String userId = "brown";
		/*** When ***/
		UserVO userVO = dao.getUser(userId);
		/*** Then ***/
		assertEquals("브라운", userVO.getName());
		assertEquals("곰", userVO.getAlias());
//		logger.debug("uservo : {}", userVO.getName());
	}

//	//사용자 페이징 리스트 조회
//	//*****고려사항*****
//	//몇번째 페이지 조회인지?, 페이징 몇건씩 데이터를 보여줄건지 : 쿼리 실행 파라미터
//	//정렬 순서? : 로직 --> 파라미터화 시킬 수 있다.
//	// --> 사용자 아이디 순으로 정렬
//	
//	@Test
//	public void userPagingListTest(){
//		/***Given***/
//		PageVo pageVo = new PageVo("", "");
//
//		/***When***/
//
//		/***Then***/
//
//	}

	/**
	 * Method : usersCntTest 작성자 : PC19 변경이력 : Method 설명 : 사용자 전체수 조회 테스트
	 */
	@Test
	public void usersCntTest() {
		/*** Given ***/

		/*** When ***/
		int usersCnt = dao.usersCount();

		/*** Then ***/
		assertEquals(105, usersCnt);

	}

	/**
	 * Method : insertUserTest 작성자 : PC19 변경이력 :
	 * 
	 * @throws ParseException Method 설명 : 사용자 등록
	 */
	@Test
	public void insertUserTest() throws ParseException {
		/*** Given ***/
		// 사용자 정보를 담고 있는 vo객체 준비
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserVO userVo = new UserVO("userTest", "대덕인", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩", "", "", "34940",
				sdf.parse("2019-05-31"));

		/*** When ***/
		int insertCnt = dao.insertUser(userVo);

		/*** Then ***/
		assertEquals(1, insertCnt);
	}

	/**
	 * Method : deleteUserTest 작성자 : PC19 변경이력 : Method 설명 :사용자 삭제
	 */

	@Test
	public void deleteUserTest() {
		/*** Given ***/

		/*** When ***/
		int result = dao.deleteUser("user39");

		/*** Then ***/
		assertEquals(1, result);
	}

	@Test
	public void modiUserTest() throws ParseException {
		/*** Given ***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserVO userVo = new UserVO("user39", "대덕인", "중앙로", "userTest1234", "대전광역시 중구 중앙로76", "영민빌딩", "", "", "34940",
				sdf.parse("2019-05-31"));

		/*** When ***/
		int result = dao.modiUser(userVo);

		/*** Then ***/
		assertEquals(1, result);
	}
}
