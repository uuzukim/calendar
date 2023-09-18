
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.temporal.TemporalField"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.YearMonth"%>
<%@page import="kr.or.ddit.servlet03.CalendarInfo"%>
<%@page import="java.text.MessageFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	CalendarInfo infoVO = (CalendarInfo) request.getAttribute("infoVO");
	YearMonth targetYM = infoVO.getTargetYM();
	YearMonth beforeYM = infoVO.getBeforeYM();
	YearMonth nextYM = infoVO.getNextYM();
	Locale locale = infoVO.getLocale();
	ZoneId zone = infoVO.getZone();
	WeekFields weekFields = infoVO.getWeekFields();
	DayOfWeek firstDayOfWeek = infoVO.getFirstDayOfWeek();
	TemporalField dayOfWeek = infoVO.getDayOfWeek();
%>

<h4>
<a href="javascript:;" class="changer" data-year="<%=beforeYM.getYear() %>" data-month="<%=beforeYM.getMonthValue() %>">전달</a>
<%=targetYM.format(DateTimeFormatter.ofPattern("uuuu, MMMM",locale)) %>
<a href="javascript:;" class="changer"data-year="<%=nextYM.getYear() %>" data-month="<%=nextYM.getMonthValue() %>">다음달</a>
</h4>
<h4>first day of week : <%=firstDayOfWeek %></h4>
<table>
	<thead>
		<tr>
			<%
					   for(int cnt=0; cnt<7; cnt++){
			               DayOfWeek thisTurn = firstDayOfWeek.plus(cnt);
			               out.println(
			                  MessageFormat.format("<th class=''{1}''>{0}</th>"
			                  , thisTurn.getDisplayName(TextStyle.FULL, locale)
			                  , thisTurn.name().toLowerCase())
			               );
			            }
			%>
		</tr>
	</thead>
	<tbody>
		<%
			LocalDate firstDate = targetYM.atDay(1); //  6월 1일이라는 뜻. 1일. 
			int offset = firstDate.get(dayOfWeek)-firstDayOfWeek.get(dayOfWeek);// 이요일이 몇번째인지가 필요해.// 경우에 따라서 4나 5를 가지고 있다.  
			LocalDate startDate = firstDate.minusDays(offset); // 5월 28일 
			int count = 0;
			for(int row=0; row<6;row++){
				out.println("<tr>");
				for(int col=0; col<7;col++){
					LocalDate thisTurn = startDate.plusDays(count++);
					YearMonth thisTurnYM = YearMonth.from(thisTurn);
					StringBuilder classes = new StringBuilder();
					classes.append(
						thisTurnYM.isBefore(targetYM) ? "before" : 
						thisTurnYM.isAfter(targetYM) ? "after" : 
							thisTurn.getDayOfWeek().name().toLowerCase()
					);
					out.println(
						MessageFormat.format("<td class=''{1}''>{0}</td>"
											, thisTurn.getDayOfMonth()
											, classes)		
					);
				}
				out.println("</tr>");
			}
		%>
	</tbody>
</table>