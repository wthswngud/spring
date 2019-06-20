package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {
	@Resource(name = "collectionBean")
	private IocCollection collection;
	
//	@Resource(name = "collectionBean")
//	private Map<String, String> map;
//	
//	@Resource(name = "collectionBean")
//	private Set<String> set;
//	
//	@Resource(name = "collectionBean")
//	private Properties properties;
	
	@Test
	public void SpringIocTest() {
		/***Given***/
		

		/***When***/

		/***Then***/
		assertEquals("brown" , collection.getList().get(0));
		assertEquals("brown", collection.getMap().get("name"));
		assertTrue(collection.getSet().contains("brown"));
		assertEquals("brown", collection.getProperties().get("userId"));
	}
}
