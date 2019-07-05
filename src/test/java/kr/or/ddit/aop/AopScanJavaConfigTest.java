package kr.or.ddit.aop;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.service.IboardService;
import kr.or.ddit.testenv.LogicTestEnv;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AopScanConfig.class})
public class AopScanJavaConfigTest extends LogicTestEnv{
	@Resource(name = "boardServiceimpl")
	private IboardService boardService;
	
	@Test
	public void aopBeforeTest() {
		/***Given***/
		

		/***When***/
		String msg = boardService.sayHello();
		/***Then***/
		assertEquals("boardDao sayHello", msg);
	}
	
}
