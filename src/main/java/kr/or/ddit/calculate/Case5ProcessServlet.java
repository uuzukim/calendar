package kr.or.ddit.calculate;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;

/**
 * 1. request parameter / html response
 * 2. request parameter / json response
 * 
 * 3. request json payload / html response
 * 4. request json payload / json response
 */
@WebServlet("/calculate/Case5ProcessServlet")
public class Case5ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper mapper = new ObjectMapper();
	
	//얘는 파라미터값 보냈을때 사용
	private CalculateVO dataFromParameter(HttpServletRequest request) throws Exception{
			
			String leftOp = request.getParameter("leftOp");
			String rightOp = request.getParameter("rightOp");
			String opParam = request.getParameter("opParam");	
			
			double left = Double.parseDouble(leftOp);
			double right = Double.parseDouble(rightOp);			
			OperatorType operator = OperatorType.valueOf(opParam);
			
			CalculateVO target = new CalculateVO();
			target.setLeftOp(left);
			target.setRightOp(right);
			target.setOpParam(operator);
			
			return target;
	}
	
	//얘는 제이슨타입 보냈을때 사용
	private CalculateVO dataFromJsonPayload(HttpServletRequest request) throws IOException {
		try(
				InputStream is = request.getInputStream(); // 바디의 데이터 읽어온다. 
			){
				return mapper.readValue(is, CalculateVO.class); // InputStream에서 읽은 json데이터를 cacultateVO 객체로 변환한다.
				// mapper객체는 json데이터를 java객체로 역직렬화하는 역활을 담당한다. 
				// readValue메소드의 첳번째 인자로는 변환할 json데이터가 있는 inputstream을 전달하고 
				// 두번재 인자로는 변환할 클래스 타입(calculateVO.class)를 전달한다. 
				// 즉 이 코드는 json페이로드로부터 'calculateVO'객체를 생성하여 반환하는 역활을 한다. 
				// 이를 통해 클라이언트가 JSON형식으로 데이터를 전송했을 때 서버에서 해당 데이터를 객체로 변환하러 처리할 수 있다. 
			}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String requestContentType = Optional.ofNullable(request.getContentType()).orElse("");
									//optional.ofnullable() 메서드를 사용하여 'request.getContentType()'의 반환값이 null 일경우 빈 문자열로 초기화.
									// 이걸 왜써? 헤더가 설정되지 않은경우나 잘못된요청이 발생하는 경우 null이 반환되면 nullpoint가 발생. 
									// 그런 예외를 방지하고 코드 안정성을 높이기 위해 씀. 
		try {
			CalculateVO target = null;
			if(requestContentType.contains("json")) {
				target = dataFromJsonPayload(request);
			}else if(requestContentType.contains("xml")){
				response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
				return;
			}else {
				target = dataFromParameter(request);
			}
			
			
			String accept = request.getHeader("accept");
			String responseContentType = null;
			Object content = null;
			
			if(accept.contains("json")) {
				responseContentType = "application/json;charset=utf-8";
				content = mapper.writeValueAsString(target); // 마샬린작업 . 직렬화  
			}else if(accept.contains("xml")) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
				return;
			}else {
				responseContentType="text/html;charset=utf-8";
				content=target.getExpr(); // expr에 있는 연산식이 가져오겠지 
			}
			
			response.setContentType(responseContentType);
			
			try (
				PrintWriter out = response.getWriter();
			){
				out.println(content);// 이건 직렬화만 한다는 얘기, 바디에 기록만 하겠다는 뜻
			}
		}catch(Exception e) {
			response.sendError(400); // 여기에 걸리면 두개의 피연산자중에 잘못된 데이터가 넘어왔다는것. 아님 다른 연산을 요구한것. 
		}
		
	}

}
