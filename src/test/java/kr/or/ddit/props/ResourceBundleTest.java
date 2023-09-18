package kr.or.ddit.props;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import kr.or.ddit.IndexController;

/**
 * class path resource 검색 : ClassLoader
 *
 * A a = new A(); 1. 인스턴스가 생성되려면 버츄얼머신은 클래스로더를 이용해서 A라는 클래스를 상수메모리공간에 저장시킨다.
 * 상수 메모리공강은 한번 저장되면 삭제가 되지 않는다. 
 * 상수 메모리공간에 복사를해서 인스턴스를 생성하고 그렇게 생성한 인스턴스를 힙 메모리에 저장한다. 
 * 2. 앞에 A랑 뒤에 A는 다른공간에 적재된다. 그럼 주소가 필요하겠찌 
 * A b = new A(); 그럼 여기서는 작업이 2번이루어지겠찌 이미 A가 만들어졌으니까 
 * 
 * 
 * properties 파일용 API : Properties, ResourceBundle
 */
class ResourceBundleTest {
	
	@Test
	void readTest() {
		
		String baseName="kr.or.ddit.messages.Message";
		// baseName에는 확장자가 포함되지 않는다. 
		// baseName에는 앞에 /가 들어가지 않는다.
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, Locale.ENGLISH);
		Enumeration<String> keys =  bundle.getKeys();
		while (keys.hasMoreElements()) {
			String msgCode = (String) keys.nextElement();
			String msg = bundle.getString(msgCode);
			System.out.printf(" %s : %s \n", msgCode, msg);
		}
	}
}
