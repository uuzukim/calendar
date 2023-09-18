package kr.or.ddit.servlet02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/gugudan.do")
public class GugudanServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 지역변수 
		int minDan = 2;
		int maxDan = 9;
		
		String minParam = req.getParameter("minDan");
		String maxParam = req.getParameter("maxDan");
		
		if(minParam != null && maxParam != null){
			try{//상태코드 변경 이거 왜해? -> 우리가 입력을 숫자만 받으려고 했는데 사용자가 이상한ADFAD문자 입력했어 이거 누구 잘못? 사용자 잘못. 근데 500에러가 뜨네? 그럼 바꿔줘야지 
				minDan = Integer.parseInt(minParam);
				maxDan = Integer.parseInt(maxParam);
			}catch(NumberFormatException e){
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);// 클라이언트가 잘못된 요청.
				return;
			}
		}
		req.setAttribute("minDan", minDan);
		req.setAttribute("maxDan", maxDan);
		//String viewName="/WEB-INF/views/02/gogodan.jsp";
		
		String logicalViewName = "02/gogodan";
	
		// contentpage= 마일드 디스패처가 만듬 
		//	String contentPage="/WEB-INF/views/02/gogodan.jsp";
		// req.setAttribute("contentPage", contentPage);
		
		String viewName="/"+logicalViewName+".tiles";
		req.getRequestDispatcher(viewName).forward(req,resp);
	}
}
