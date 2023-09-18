<%@page import="java.util.Map"%>
<%@page import="java.util.Optional"%>
<%@page import="java.time.Month"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.time.temporal.TemporalField"%>
<%@page import="java.time.format.TextStyle"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.time.temporal.WeekFields"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.YearMonth"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.ZoneId"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
	
	//targetYM = YearMonth.of(yearValue, monthValue);
	
	WeekFields weekFields = WeekFields.of(locale);
	DayOfWeek firstDayOfWeek = weekFields.getFirstDayOfWeek();
	TemporalField dayOfWeek = weekFields.dayOfWeek();// 요일단위 재설정 
	
	YearMonth beforeYM = targetYM.minusMonths(1);
	YearMonth nextYM = targetYM.plusMonths(1);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.before,.after{
	color : silver;
}
.<%=DayOfWeek.SUNDAY.name().toLowerCase()%>{
	color : red;
}
.<%=DayOfWeek.SATURDAY.name().toLowerCase()%>{
	color : blue;
}
td,th{
	border: 1px solid black;
	text-align : center;
}
table{
	border-collapse: collapse;/*테이블 중복선없애기*/
	width:100%;
	height:500px;
}

</style>
</head>
<body>
<H4>TODAY : <span id="todayArea"></span></H4>
<h4>server's CURRENT : <span id="currentArea"></span></h4>
<hr />

<form id = "calendarForm" onchnage="this.requestSubmit()" method="post">
	<input type ="number" name="year" data-year="<%=targetYM.getYear()%>" />
	<select name="month" data-month ="<%=targetYM.getMonthValue() %>" >
		<%=
			//여기서 이제 옵션을 만들어야해 . 문자로 만들어야하고 데이터가 있어야해
			Stream.of(Month.values())
				.map(m->{
					return MessageFormat.format("<option value=''{0}''>{1}</option>"
									, m.getValue()
									, m.getDisplayName(TextStyle.FULL, locale));
				// enum  상수 하나 받아서 문자열로 바꾼것. 
				}).collect(Collectors.joining("\n"))
		%>
	</select>
	<select name="locale" data-locale="<%=locale.toLanguageTag()%>">
		<%=
			Stream.of(Locale.getAvailableLocales())
					.filter(l-> ! l.getDisplayName(l).isEmpty())
					.map(l->{
						//locale code(locale tag) ex) ko_KR // 언어_국가 
						return MessageFormat.format("<option value=''{0}''>{1}</option>" 
											,l.toLanguageTag()
											,l.getDisplayName(l) );
					}).collect(Collectors.joining("\n"))

		%>
	</select>
	<select name="zone" data-zone="<%=zone.getId()%>">
	
		<%=
			ZoneId.getAvailableZoneIds().stream()
				.map((z)->MessageFormat.format("<option value=''{0}''>{1}</option>"
							, z
							, ZoneId.of(z).getDisplayName(TextStyle.FULL,locale)))
				.collect(Collectors.joining("\n"))
		
		%>
		</select>
</form>

<h4>
<a href="javascript:;" class="changer" data-year="<%=beforeYM.getYear() %>" data-month="<%=beforeYM.getMonthValue() %>">전달</a>
<%=targetYM.format(DateTimeFormatter.ofPattern("uuuu, MMMM",locale)) %>
<a href="javascript:;" class="changer"data-year="<%=nextYM.getYear() %>" data-month="<%=nextYM.getMonthValue() %>">다음달</a>
</h4>
<h4>first day of week : <%=weekFields.getFirstDayOfWeek() %></h4>


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

<script type="text/javascript">
	// 비동기 요청으로 시간 바뀌기//
	//JS scheduling function : setTimeout(지연시간셋팅), setInterval// timeout은 대기시간.기다렸따가 한번실행하고 끝 // INTERVAL 주기를 설정해놓고 반복하고 싶을때 
	setInterval(() => {
		fetch("<%=request.getContextPath()%>/05/serverTime", {
			mehod:"get",
			headers:{
				"Accept" : "application/json"// ajax dataType 과 동일 
			}
		}).then(resp=>resp.json())
		.then(respObj=>{
			currentArea.innerHTML = respObj.current;
			todayArea.innerHTML = respObj.today;
		});		
	}, 1000);
	
	calendarForm.querySelectorAll("[name]").forEach((ipt)=>{
		let name = ipt.name;
		let data = ipt.dataset[name];
		if(data)
			ipt.value = data;
	});
	
	document.querySelectorAll(".changer").forEach(atag=>{
		atag.addEventListener("click", (event)=>{
			
			calendarForm.year.value = event.target.dataset.year; // 2023
			calendarForm.month.value = event.target.dataset.month; // 6
			
			calendarForm.requestSubmit();// 폼전송 
			
		});
	});
</script>
</body>
</html>