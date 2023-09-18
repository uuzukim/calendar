package kr.or.ddit.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session  = req.getSession(false);
		if(session !=null && !session.isNew()) {
//			session.removeAttribute("authId"); 
			session.invalidate(); // 세션을 무효화하여 세션과 관련된 데이터를 모두 제거
		}
		resp.sendRedirect(req.getContextPath()+"/");
	}
	
}
