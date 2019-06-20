package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

//spring bean 이름 : 인스턴스 생성규칙 --> 클래스명에게 첫글자를 소문자로
//spring bean 이름을 임의로 주고싶은 경우 @Repository("bDao")

public interface IboardDao {
	String sayHello();
}
