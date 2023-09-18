<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	//request.getRequestDispatcher("").include(request,response);
	String preScript = (String)request.getAttribute("preScript");
	pageContext.include(preScript);
	// pageContext : 포워드,디스패치 이런거 안해도 더 간단하게 가져올수 있음.
	
%>
</head>
<body>
<header>
	<jsp:include page='<%=(String)request.getAttribute("headerMenu") %>'/>
</header>
<main>
<%
	// 컨텐츠 들어가는 구멍 
	// view로 쓰려고함 
	String contentPage = (String)request.getAttribute("contentPage");
	pageContext.include(contentPage);
%>
</main>
<footer>
	<jsp:include page='<%=(String)request.getAttribute("footer") %>' />

</footer>
<!-- 이건 백그라운드태그 -->
<jsp:include page='<%=(String)request.getAttribute("postScript") %>' />
</body>
</html>