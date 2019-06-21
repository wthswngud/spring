package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formmating.xml")
public class ApplicationIocTypeFormattingTest {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationIocTypeFormattingTest.class);
	
	@Resource(name = "formattingVo")
	private FormattingVo forVO;
	
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	private mod_dt;

	@Test
	public void test() {
		/***Given***/
		

		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("MM-yyyy-dd");
		String reg_dt = sdf.format(forVO.getReg_dt());
		String mod_dt = sdf.format(forVO.getMod_dt());

		/***Then***/
		assertNotNull(forVO);
		logger.debug("forVO : {}", forVO);
		logger.debug("reg_dt:{}", reg_dt);
		logger.debug("reg_dt:{}", mod_dt);
		
		assertEquals("06-2019-16", reg_dt);
		assertEquals("06-2019-21", mod_dt);
		assertEquals(6000, forVO.getNumber());
	}
}
