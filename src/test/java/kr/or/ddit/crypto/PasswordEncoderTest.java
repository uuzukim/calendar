package kr.or.ddit.crypto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

class PasswordEncoderTest {

	private static PasswordEncoder passwordEncoder;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Test
	void test2() {
		String savedPass = "{bcrypt}$2a$10$xJ9Em4MkRyaY3kETvdzuT.rnMBqEm23B5oTdX46RBuQnfT8MmLm9u";
		String inputPass = "java";
		boolean authenticated = passwordEncoder.matches(inputPass, savedPass);
		System.out.println(authenticated);
		
		//passwordEncoder가 inputpass를 암호화해서 둘을 비교해서 확인함. 리턴타입은 불린. 같다. 다르다. 
	}
	
	@Test
	void test() {
		String plain ="java";
		String encoded = passwordEncoder.encode(plain);
		System.out.println(encoded);
	}

}
