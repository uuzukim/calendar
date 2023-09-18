package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@WebServlet("/05/serverTime")
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Refresh", "1"); 
		// ㄴ동기요청에 대해서만 동작하는 헤더
		// ㄴ1초마다 한번씩 해드를 리워딩해줘. 단점은 화면전체를 다시 리워딩하기 때문에 (동기방식으로)다른작업이 어렵다. 
		ZoneId zone = Optional.ofNullable(request.getParameter("zone"))
				//sugar syntax : 문법이 허용하는 범위 내에서 간결하게 코드를 작성하는 방식. 
							.map(zp->ZoneId.of(zp))//형태 변경하는 방법 zp는 스트링타입 . 
							.orElse(ZoneId.systemDefault());
		LocalDate TODAY = LocalDate.now(zone);
		LocalDateTime CURRENT = LocalDateTime.now(zone);
		Locale locale = Optional.ofNullable(request.getParameter("locale")) 
								.map(lp->Locale.forLanguageTag(lp))// lp를 locale객체로 만드는중
								.orElse(request.getLocale());
		
		request.setAttribute("today", TODAY);
		request.setAttribute("current", CURRENT);
		request.setAttribute("zone", zone.getDisplayName(TextStyle.FULL, locale));
		
		String viewName="/jsonView.view";
		request.getRequestDispatcher(viewName).forward(request, response);
		
	}
	
}
