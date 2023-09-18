<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
// 	String savedId = Optional.ofNullable(request.getCookies())
// 								.map(ca->
// 									Stream.of(ca)
// 											.filter(c->"idCookie".equals(c.getName()))// filter 아이디를 찾을 수 있는 조건을 주는것 
// 											// 이 조건에 맞는 조건을 찾으면? 근데 이게 있을수 있고 없을수 있는것 
// 											.findFirst()
// 											.map(Cookie::getValue) // 이거랑 같은뜻 fc->fc.getValue(). 
// 																	// 메소드 레퍼런스를 사용할 수 있는 방법은?	 
// 											.orElse(null)// stream을 대상으로 한 orelse
// 								).orElse(null); // optional에 대한 orelse
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color :red;	
	}
</style>
</head>
<body>
<c:if test="${not empty message }">
<div class="error">
 	 ${message} <%-- %{}이렇게 쓰면 NULL일때 NULL로 안뜨고 아무것도 안뜬다.  --%>
 	 <c:remove var="message" scope="session"/>

</div>
</c:if>
<form action="${pageContext.request.contextPath }/login/loginProcess" method="post">
	<ul>
		<li>
			아이디 : <input type="text" name="memId" value="${cookie.idCookie.value }"/>
			<input type="checkbox" name="idSave" ${not empty cookie.idCookie ? "checked":"" }>아이디저장 
		</li>
		<li>
			비밀번호 : <input type="text" name="memPass" />
		</li>
		<li>
			<button type="submit">로그인</button>
		</li>
	</ul>
</form>
</body>
</html>