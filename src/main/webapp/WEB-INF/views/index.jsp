<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4><%=(String)request.getAttribute("welcomeMessage") %></h4>
<h4>${welcomeMessage}</h4>

<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if(authMember!=null){// 있나 없나 확인 
		%>
		<h4><%=authMember.getMemName() %>님 로그인 성공 </h4>
	
		<a href="javascript:;" onclick="logoutForm.requestSubmit()">로그아웃</a>
		<%
	}else{
		%>
		<a href="<%=request.getContextPath() %>/login/loginForm.jsp">로그인</a>			
<%
	}
	//session.removeAttribute("authId") // 로그인하고 로그아웃하기까지 살아있어야 함 
%>

