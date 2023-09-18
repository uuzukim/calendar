package kr.or.ddit;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.simple.AbstractController;


public class IndexController extends AbstractController {
    @Override   
	public String getHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.setAttribute("welcomeMessage", "웰컴 페이지용 안내 메시지 ");
    	return "index";
	}


}
