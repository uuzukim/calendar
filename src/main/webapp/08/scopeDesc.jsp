<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Scope(4) : (속성-attribute 저장 영역)</h4>
<pre>
	:  생명주기가 다른 기본 객체가 관리하는 메모리 공간 (각 객체를 만들고 그 객체가 사라지면 저장공간도 사라짐)
	pageContext(pageScope) : 하나의 JSP페이지와 생명주기 동일(페이지를 벗어나는 순간. 사람짐) 
	request(requestScope) : Http stateless 특성에 따라 하나의 요청과 동일한 생명주기(해당요청이 사라질떄 사라짐)
	session(sessionScope) : 한 세션과 동일한 생명주기 (세션종료하면 사라짐)
	application(applicationScope) : 하나의 컨텍스트와 동일한 생명주기(서버나 어플리케이션 정보를 가진 객체가 사라지면 사라짐)
	
	setAttribute(String name, Object value)
	Object getAttribute(String nanme)
	removeAttribute(String name)
	
	<%
		pageContext.setAttribute("pageAttr", "페이지 속성");
		request.setAttribute("requestAttr", "요청 속성");
		session.setAttribute("sessionAttr", "세션 속성");
		application.setAttribute("applicationAttr", "어플리케이션 속성");
		
		//RequestDispatcher rd = request.getRequestDispatcher("/08/attrView.jsp");
		//rd.include(request, response);
		
		response.sendRedirect(request.getContextPath()+"/08/attrView.jsp");
	%>
	
	<a href ="attrView.jsp">속성 확인하기</a>
	
</pre>
</body>
</html>