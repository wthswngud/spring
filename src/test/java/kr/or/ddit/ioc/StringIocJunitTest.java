package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IboardDao;
import kr.or.ddit.board.service.IboardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class StringIocJunitTest {

	@Resource(name = "boardDaoImpl")
	private IboardDao boardDao;
	
	@Resource(name = "boardDaoPrototype")
	private IboardDao boardDaoPrototype;
	
	@Resource(name = "boardDaoPrototype")
	private IboardDao boardDaoPrototype2;
	
	@Resource(name="boardService")
	private IboardService boardService;
	
	@Resource(name="boardService")
	private IboardService boardService2;
	
	@Resource(name="boardServiceConstructor")
	private IboardService boardServiceConstructor;
	
	/**
	* Method : springIocTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		/***Given***/
		
		/***When***/
		String msg = boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
	}
	
	/**
	* Method : springSingletonScoteTest
	* 작성자 : PC19
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너의 singleton scope 테스트
	*/
	@Test
	public void springSingletonScoteTest() {
		// filed 기준  boardService, boardService2 : spring boardService bean
		// 			 boardServiceConstructor : spring boardServiceConstructor bean(scope=signleton)
		// 1. boardService, boardService2 : 같아야함
		// 2. boardService, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
		// 3. boardService2, boardServiceConstructor : 같은 클래스에서 만들어진 객체이지만 spring singleton 개념에 따라 다른 객체
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		assertEquals(boardService, boardService2);
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}
	
	@Test
	public void springPrototypeScopeTest() {
		// boardDao : spring boardDao(scope="singleton")
		// boardDaoPrototype : spring boardDaoPrototype(scope="prototype")
		// boardDaoPrototype2 : spring boardDaoPrototype(scope="prototype")
		// 1. boardDao boardDaoPrototype : spring bean id가 다르므로 다른객체
		// 1. boardDaoPrototype boardDaoPrototype2 : 두객체는 같은 스프링 빈이지만
													 // scope가 prototype이므로 다른 객체여야한다.
		/***Given***/
		

		/***When***/

		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
	}
}
