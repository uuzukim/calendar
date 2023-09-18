package kr.or.ddit.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


/**
 * Json Content를 생성하기 위한 view layer // json마샬린만 하는 클래스. 
 *
 */
@WebServlet("/jsonView.view")
public class JsonViewServlet extends HttpServlet {    
	
	private ObjectMapper mapper = new ObjectMapper()
									.registerModule(new JavaTimeModule()) //마샬링하려고 하는것 날짜 및 시간 api를..
									.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
									
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");		
		
		Map<String, Object> target = new HashMap<>();// json으로 변환할 데이터를 담을 맵 객체 생성
		Enumeration<String> attrNames = req.getAttributeNames(); // 요청의속성 이름들을 가져온다.
		while (attrNames.hasMoreElements()) { // 속성 이름을 반복하면서 각 속성의 이름과 값을 맵 객체에 추가한다.
			String name = (String) attrNames.nextElement();
			Object value = req.getAttribute(name);
			target.put(name, value);
		}
		try(
			PrintWriter out = resp.getWriter(); // 응답을 생성하기 위해 printWriter생성
		){
			mapper.writeValue(out, target); // target을 json으로 직렬화 하여 printWriter를 통해 클라이언트로 응답 보냄. 
		}
	}

}
