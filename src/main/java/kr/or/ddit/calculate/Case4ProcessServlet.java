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

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/calculate/Case4ProcessServlet")
public class Case4ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper mapper = new ObjectMapper();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		// 요청 생성과정
		
		//String contentType = request.getContentType();// 여기서반환되는경우에느 null이 있을 수 있따(아래한글..)
		String contentType = Optional.ofNullable(request.getContentType()) // 이 데이터는 있을수도 있고 없을 수 도 있다. 
									 .orElse(""); // 없을때 사용하는 기본값 셋팅 
		int status = HttpServletResponse.SC_OK;
		
		CalculateVO target = null;
		if(contentType.contains("json")) { // payload로 전송
			// 여기서는 언마샬링을 해야해. 언마샬링 하려면 read계열이 필요해  
			try(
				InputStream is = request.getInputStream();
			){
				target = mapper.readValue(is, CalculateVO.class);
			}
			
		}else {// 파라미터로 전송
			try {
			String leftOp = request.getParameter("leftOp");
			String rightOp = request.getParameter("rightOp");
			String opParam = request.getParameter("opParam");	
			
			double left = Double.parseDouble(leftOp);
			double right = Double.parseDouble(rightOp);			
			OperatorType operator = OperatorType.valueOf(opParam);
			
			target = new CalculateVO();
			target.setLeftOp(left);
			target.setRightOp(right);
			target.setOpParam(operator);
		}catch (Exception e) {
			status = HttpServletResponse.SC_BAD_REQUEST;
			e.printStackTrace();
			}
		}
		
		
		//응답 생성과정
			response.setContentType("application/json;charset=utf-8");
			
			try (
				PrintWriter out = response.getWriter();
			){
				//out.println(expr);
				//마샬린할꺼야(직렬화도 같이)
				mapper.writeValue(out, target);
			}
//			response.sendError(400); // 여기에 걸리면 두개의 피연산자중에 잘못된 데이터가 넘어왔다는것. 아님 다른 연산을 요구한것. 
		}
		
	}


