package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-convert.xml")
public class ApplicationIocTypeConvertTest {
	@Resource(name="userVO")
	private UserVO userVO;
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeConvertTest.class);

	@Test
	public void ApplicationIocTest() {
		/***Given***/
		

		/***When***/
		Date birth = userVO.getBirth();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birth_str = sdf.format(birth);
		
		/***Then***/
		assertNotNull(userVO);
		assertEquals("brown", userVO.getUserId());
		logger.debug("birth_str : {}", birth_str);
		
	}
}
