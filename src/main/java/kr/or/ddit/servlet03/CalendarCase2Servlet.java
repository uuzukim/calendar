package kr.or.ddit.servlet03;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 특정 년도와 월, 로케일, 시간대 파라미터를 이용해 달력을 처리하는 컨트롤러(Model 2)
 * 
 */
@WebServlet("/calendarCase2")

public class CalendarCase2Servlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int yearValue = Optional.ofNullable(request.getParameter("year"))// 없을수도 있어. 없으면 예외가 발생해야 하니까 써줌 
								.map(yp->Integer.parseInt(yp)) // 파라미터있을때만 동작 
								.orElse(-1); // 없으면 기본값 -1
		int monthValue = Optional.ofNullable(request.getParameter("month"))
								.map(mp->Integer.parseInt(mp))
								.orElse(-1);
		
		Locale locale = Optional.ofNullable(request.getParameter("locale"))
				.map(lp->Locale.forLanguageTag(lp))	
				.orElse(Locale.getDefault());

		ZoneId zone = Optional.ofNullable(request.getParameter("zone"))
				.map(zp->ZoneId.of(zp))
				.orElse(ZoneId.systemDefault());
		
		//response.setHeader("Refresh", "1"); // 1초마다 한번씩 해드를 리워딩해줘. 단점은 화면전체를 다시 리워딩하기 때문에 (동기방식으로)다른작업이 어렵다. 
		// Locale locale = Locale.KOREA;
		// ZoneId zone = ZoneId.systemDefault();
		LocalDate TODAY = LocalDate.now(zone);
		//LocalDateTime CURRENT = LocalDateTime.now(zone);
		YearMonth targetYM = YearMonth.now(zone);
		
		if(yearValue != -1 && monthValue !=-1){
			targetYM = YearMonth.of(yearValue, monthValue);
		}
		
		CalendarInfo infoVO = new CalendarInfo(targetYM, locale, zone);
		
		request.setAttribute("infoVO", infoVO);
		
		String viewName = "/WEB-INF/views/calendar/calView.jsp";
		request.getRequestDispatcher(viewName).forward(request,response);
		
	}
}
