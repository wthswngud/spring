package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{
	@Resource(name = "userServiceImpl")
	private IuserService service;

	@Test
	public void test() {
		/***Given***/
		
		/***When***/
		List<UserVO> userlist = service.userList();
		/***Then***/
		assertNotNull(userlist);
	}
	
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
	
	@Test
	public void deleteUserTest() {
		/*** Given ***/

		/*** When ***/
		int result = service.deleteUser("userTest");

		/*** Then ***/
		assertEquals(1, result);
	}
}
