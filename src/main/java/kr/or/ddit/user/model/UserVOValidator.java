package kr.or.ddit.user.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//데이터 검증
		UserVO userVO = (UserVO)target;
		
		//사용자 아이디 길이 4글자 이상
		if(userVO.getUserId().length()<=3)
			errors.rejectValue("userId", "length");
		// 사용자 이름 2글자 이상
		if(userVO.getName().length() < 2)
			errors.rejectValue("name", "length");
	}
}
