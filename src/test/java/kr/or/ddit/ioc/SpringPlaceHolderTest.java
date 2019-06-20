package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringPlaceHolderTest {
	@Resource(name = "dbInfo")
	DbInfo info;

	/**
	* Method : test
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : spring placeholder test
	*/
	@Test
	public void test() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals("oracle.jdbc.driver.OracleDriver", info.getDriver());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", info.getUrl());
		assertEquals("pc19", info.getUsername());
		assertEquals("java", info.getPassword());
	}
}
