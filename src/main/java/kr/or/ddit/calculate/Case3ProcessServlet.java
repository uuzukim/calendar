package kr.or.ddit.calculate;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/calculate/Case3ProcessServlet")
public class Case3ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String leftOp = request.getParameter("leftOp");
		String rightOp = request.getParameter("rightOp");
		String opParam = request.getParameter("opParam");
		
		try {
			double left = Double.parseDouble(leftOp);
			double right = Double.parseDouble(rightOp);			
			OperatorType operator = OperatorType.valueOf(opParam);
			double result = operator.biOperate(left, right);
			
			String expr = operator.expression(left, right);
			
			// native
			Map<String,Object> target = new HashMap<>();
			target.put("expr", expr);
			target.put("result", result);
			target.put("left", left);
			target.put("right", right);
			target.put("operator", operator);
			
			String accept = request.getHeader("accept");
			String contentType = null;
			Object content = null;
			
			if(accept.contains("json")) {
				contentType = "application/json;charset=utf-8";
				content = new ObjectMapper().writeValueAsString(target); // 마샬린작업 . 
			}else if(accept.contains("xml")) {
				response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
				return;
			}else {
				contentType="text/html;charset=utf-8";
				content=target.get("expr"); // expr에 있는 연산식이 가져오겠지 
			}
			
			response.setContentType(contentType);
			
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
