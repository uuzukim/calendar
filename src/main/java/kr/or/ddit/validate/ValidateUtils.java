package kr.or.ddit.validate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import kr.or.ddit.validate.groups.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

public class ValidateUtils {
	// 여기가 결국은 hibernate validation을 쓰기 위해 만든 클래스 
	// 검증하기 위한 클래스 
	private static Validator validator;
	
	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	public static <T> boolean validate(T target, Map<String, String> errors, Class<?>... groups ){ // 그룹파라미터? /가변파라미터는 언제나 마지막에 있어야해
		Set<ConstraintViolation<T>> violations = validator.validate(target, groups);// 그룹이 없다면 기본으로 사용 되는 것. 
		boolean valid = violations.isEmpty(); 
		if(!valid) { // 통과 못해서 이 안에는 결과가 쌓여있어야한다. 
			//Map<String, String> errors = new LinkedHashMap<>();
			 // 누가 어떤검증을 통과하지 못했는지 확인해야해. 
			violations.stream().forEach(sv->{
				String key = sv.getPropertyPath().toString();
				String value = sv.getMessage();
				errors.put(key, value);
				System.out.printf("%s : %s\n", key, value);
			});
		}
		
		return valid;
	}
}
