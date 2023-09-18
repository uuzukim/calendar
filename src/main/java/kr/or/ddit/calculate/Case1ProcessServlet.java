package kr.or.ddit.calculate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/calculate/Case1ProcessServlet")
public class Case1ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//case1번과 case2번의 차이점은 html로 받았느냐, json으로 받았느냐의 차이. 
		
		String leftOp = request.getParameter("leftOp");
		String rightOp = request.getParameter("rightOp");
		String opParam = request.getParameter("opParam");
		
		try {
			double left = Double.parseDouble(leftOp);
			double right = Double.parseDouble(rightOp);			
			OperatorType operator = OperatorType.valueOf(opParam);
			double result = operator.biOperate(left, right);
			
			String expr = operator.expression(left, right);
			
			response.setContentType("text/html;charset=utf-8");
			
			try (
				PrintWriter out = response.getWriter();
			){
				out.println(expr);
			}
		}catch(Exception e) {
			response.sendError(400);
		}
		
	}

}
