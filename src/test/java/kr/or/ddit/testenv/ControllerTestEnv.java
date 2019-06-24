package kr.or.ddit.testenv;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:kr/or/ddit/config/spring/application-context.xml",
						"classpath:kr/or/ddit/config/spring/root-content.xml",
						"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
						"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
public class ControllerTestEnv {
	@Autowired
	protected WebApplicationContext ctx;	// spring container
	protected MockMvc mockMvc; 			// dispatcher servlet
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void dummy() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals("", "");
	}
}
