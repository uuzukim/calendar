<%@page import="org.apache.tiles.request.Request"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 기본객체 (내장객체, 지역변수)</h4>
<pre>
	// 우리는 9개의 기본객체를 가지고 있다. 기본 8개 나오는데 위에 isErrorPage="true"를 추가하면 exception이 나온다. 
	: 개발자가 선언하거나 생성하지 않고 컨텍스트에서 기본으로 제공되는 객체. 
	request (HttpServletRequest) : 
	response (HttpServletResponse)
	out (JspWriter)
	
	session (HttpSession): <a href="sessionDesc.jsp">session desc 참고 </a>
	application(ServletContext)  : <a href="applicationDesc.jsp" >application desc 참고, <%=application.hashCode() %></a> 
								 : context랑 application이랑 같은 의미의 뜻.. 
								 : 가장 긴 생명주기 
	config(ServletConfig) : 한개의 서블릿의 설정정보를 가지고 있는 객체 / 근데 이걸 지금 jsp에서 사용하고 있네? 그말은 jsp는 서블릿이다. 
	page(Object) : this, jsp 스펙에 따라 컨테이너가 생성한 싱글턴 인스턴스의 참조
	
	exception : 에러 처리 목적의 페이지에서 발생한 예외나 에러에 대한 정보를 가진 객체.  
	
	***pageContext*** : 가장먼저 생성되는 기본객체로, JSP 페이지에 대한 모든 정보를 가진 객체. 
						--> 나머지 기본 객체들에 대한 참조를 소유함. --> EL에서 주로 활용.  
					  : pagecontext에는 요청정보(url, 요청메서드, 헤더 등), 
					  				   응답정보(응답코드,헤더, 본문 등 ), 
					  				   세션정보(사용자id,로그인상태, 세션변수 등 ), 
					  				   사용자정보(사용자이름, 이메일, 역할 등), 
					  				   라우팅정보(경로 매개변수, 쿼리 매개변수, 라우트 이름 등) 등이 포함된다.
					   : 현재 페이지를 벗어날수 없다. 생명주기는 가장김 
		<%=pageContext.getRequest() %>				  				    					 
		<%=pageContext.getResponse() %>				  				    					 
		<%=pageContext.getSession() %>				  				    					 
		<%=pageContext.getServletContext() %>				  				    					 
		<%=pageContext.getException() %>				  				    
		==> <%=request %>, ${pageContext.request}								 
	// $중괄호: 표현언어 EL 얘는 위에 기본객체가 지원이 안된다. 근데 pageContext는 가능 .pageContext는 위에 모든 객체를 가지고 있다. 
</pre>
</body>
</html>