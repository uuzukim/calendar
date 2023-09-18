package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/localeView.nhn")
public class LocaleViewServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//클라이언트 정보 가져오기
		Locale clientLocale = req.getLocale();
		
		// 서버 기본정보 가져오기
		Locale serverLocale = Locale.getDefault(); 
		// = 현재 톰캣의 locale 
		
		StringBuilder html = new StringBuilder();
		html.append("<html>    ");
		html.append("	<body> ");
		html.append(MessageFormat.format("<h4>client side locale : {0}</h4>", clientLocale));
		html.append(MessageFormat.format("<h4>server side locale : {0}</h4>", serverLocale));
		html.append("	</body>");
		html.append("</html>   ");
		
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.println(html);
		}
	
	}
}
