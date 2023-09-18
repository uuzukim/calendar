package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.SampleFormVO;

@WebServlet("/03/payloadDataProcess.do")
public class RequestPayloadProcessServlet extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  
	  ObjectMapper mapper = new ObjectMapper(); 
	  // java 객체와 json데이터 간의 변환을 처리하는데 사용한다. 
	  // ObjectMapper는 json문자열을 java객체로 변환하거나 java객체를 json문자열로 변환하는 기능을 제공합니다. 
      
	  
      req.setCharacterEncoding("UTF-8");
      
      try(
      	InputStream is = req.getInputStream();
      ){
//    	  JSON->Native 하는걸 UnMarshalling(+deserialzation)
//    	  이게 최종 복원해낸 native객체가 된다
    	  SampleFormVO target = mapper.readValue(is, SampleFormVO.class);
    	  System.out.println(target);
      }
      String accept = req.getHeader("accept");
      String contentType = null;
      Object responseContent = null;
      // if절 마샬링(Marshalling) 할꺼냐 말꺼냐 코드 
      if(accept.contains("json")) {
    	 // Native Object  
    	 Map<String,Object> target = new HashMap<>();  //n.o생성
    	 target.put("message", "요청 처리 완료. 결과 메시지 전송.");
    	 
         contentType = "application/json;charset=UTF-8"; //마샬린
         responseContent = mapper.writeValueAsString(target);
      }else {
         contentType = "text/plain;charset=utf-8";
         responseContent = "요청 처리 완료. 결과 메시지 전송.";
      }
      
      // Accept : text/html 
      // 여기서부터 직렬화 하는 코드 
      resp.setContentType(contentType);
      try(
      PrintWriter out = resp.getWriter();
      ){
         out.println(Objects.toString(responseContent, ""));
      }
   }
}
