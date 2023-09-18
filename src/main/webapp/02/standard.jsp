<%@page import="java.text.MessageFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4><%=MessageFormat.format("현재 진행중인 쓰레드명 : {0}", Thread.currentThread().getName()) %></h4>
<h4>JSP(Java Server Page)</h4>

<pre>
	: 서블릿의 파생 스펙으로 템플릿 엔진의 형태로 동작하는 스크립트 언어 (jsp가 서블릿 객체로 동작하고 있음)
	
	
	JSP container(WAS톰캣)
	: jsp 템플릿을 해석하고, 서블릿 소스를 생성하고, 컴파일을 하고, 
	 하나의 요청이 들어오면, 싱글턴 객체로 동작할 수 있는 인스턴스를 생성하고, 
	 하나의 쓰레드를 분리하고, 필요한 콜백 메소드(_jspService) 호출 
	 
	 소스의 구성요소
	 1. 정적 텍스트 : html. js, css, 일반텍스트 , forground module로 동작(프론트)
	 2. 스크립트 요소 : background module로 동작한다,<%--<% %>, <%= %> --%> 이러한 모든것을 스크립트 요소라함.   
	 	- directive(지시자) :  %lt; 지시자명 속성명="속성값"  %&gt; :현재 JSP 페이지에 대한 설정 정보 
	 		page(required_필수적) : jsp 페이지에 대한 메타 정보(contentType, import..)
	 		include(optional) : 정적 내포 (두개이상의 jsp페이지를 하나로 합칠 수 있음)
	 		taglib(optional) : 커스텀 태그를 로딩함. (아직 우리 안써봄).
	 		(include와 taglib는 차후에 우리가 쓰면서 다시 설명할예정)
	 		
	 	- scriptlet(스크립트) : <% // java code -> _jspService메소드의 지역 코드화가 된다. _jspService가 어디있어? org타고 들어간 파일안에.%>
	 	<%
	 		String sample = "SAMPLE";
	 	%>
	 	- expression(표현식) : <%=sample %>, <%=this.sample %> , <%=testMth() %>여기서 this.는 톰캣이 만들어낸 ... 뭐라고ㅠㅠ?
	 	- declaration(선언부) : <%! %> 위치에 상관없이 어디에 선언해도 전역코드화가 된다. 컴파일이 되면 미리 생성된다는것. 
	 	<%!
	 		String sample = "INSTANCE SAMPLE";
	 		private String testMth(){ //
	 			return "Test Method";
	 		}
	 		%>
		- comment(주석) : <%-- --%>
			1. foreground comment : html, js, css
			<!-- 			html commenet  -->
			2. background comment : java, jsp
			<%--jsp comment --%>
			
	3. 스크립트 요소내에서 사용되는 기본 객체 (8)
	4. EL(표현언어)
	5. JSTL(표준 태그 라이브러리)
	
	우리 수업에서 중요한부분은 2~5번부분을 어떻게 구성할 것인가. 
	
</pre>
</body>
</html>