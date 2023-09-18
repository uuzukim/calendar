package kr.or.ddit.vo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import kr.or.ddit.validate.groups.DeleteGroup;
import kr.or.ddit.validate.groups.InsertGroup;
import kr.or.ddit.validate.groups.UpdateGroup;
import oracle.jdbc.pooling.Factory;

class MemberVOTest {
	private static Validator validator;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	void testGroup() {
		MemberVO target = new MemberVO();
//		target.setMemId("a001");
//		target.setMemPass("java");
//		target.setMemMail("aa@naver.com");
//		target.setMemZip("333-333");
//		target.setMemAdd1("대전");
//		target.setMemAdd2("오류동");
//		target.setMemHp("000-000-0000");
//		target.setMemRegno1("12345");
//		target.setMemRegno2("234432");
//		target.setMemName("쏭빈");
		
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target,DeleteGroup.class);
		boolean valid = violations.isEmpty(); 
		if(!valid) { // 통과 못해서 이 안에는 결과가 쌓여있어야한다. 
			Map<String, String> errors = new LinkedHashMap<>();
			 // 누가 어떤검증을 통과하지 못했는지 확인해야해. 
			violations.stream().forEach(sv->{
				String key = sv.getPropertyPath().toString();
				String value = sv.getMessage();
				errors.put(key, value);
				System.out.printf("%s : %s\n", key, value);
			});
		}
	}
	
	@Test
	void test() {
		MemberVO target = new MemberVO();
		target.setMemId("a001");
		target.setMemPass("java");
		target.setMemMail("aa@naver.com");
		target.setMemZip("333-333");
		target.setMemAdd1("대전");
		target.setMemAdd2("오류동");
		target.setMemHp("000-000-0000");
		target.setMemRegno1("12345");
		target.setMemRegno2("234432");
		target.setMemName("쏭빈");
		
		Set<ConstraintViolation<MemberVO>> violations = validator.validate(target);
		boolean valid = violations.isEmpty(); 
		if(!valid) { // 통과 못해서 이 안에는 결과가 쌓여있어야한다. 
			Map<String, String> errors = new LinkedHashMap<>();
			 // 누가 어떤검증을 통과하지 못했는지 확인해야해. 
			violations.stream().forEach(sv->{
				String key = sv.getPropertyPath().toString();
				String value = sv.getMessage();
				errors.put(key, value);
				System.out.printf("%s : %s\n", key, value);
			});
		}
		//우리는 지금 valid가 false라고 예상하고 
		assertNotEquals(true,valid);// true가 아니여야하지 
		
		
		
	}

}
