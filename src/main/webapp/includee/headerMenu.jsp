<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap w-100 p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="javascript:;">Class3.5</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <ul class="nav px-3 col">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/property">프로퍼티 관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/member/memberList.do">회원관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/prod/prodList.do">상품관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/buyer/buyerList.do">거래처관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="${pageContext.request.contextPath }/board/boardList.do">게시판</a>
    </li>
    <%
       Map<String, String> menuMap =(Map) request.getAttribute("welcomeMenu");
       if(menuMap!=null){
          %>
          <li class="nav-item text-nowrap">
             <a class="nav-link" href="<%=menuMap.get("menuURL")%>"><%=menuMap.get("menuText") %></a>
          </li>
          <%
       }
    %>
  </ul>
  <ul class="nav px-3 col-2">
  <!-- 조건문으로 인증 여부에따라 선택정 랜더링. -->
  <%
     if(session.getAttribute("authMember")==null){
        //로그인 전 상태
     %>
       <li class="nav-item text-nowrap">
         <a class="nav-link" href="<%=request.getContextPath() %>/member/memberInsert.do">Sign up</a>
       </li>
       <li class="nav-item text-nowrap">
         <a class="nav-link" href="<%=request.getContextPath() %>/login/loginForm.jsp">Sign in</a>
       </li>
     <%
     }else{
        //로그인 성공
        %>
        <li class="nav-item text-nowrap">
           <a class="nav-link" href="${pageContext.request.contextPath }/mypage">${authMember.memName }님</a>
        </li>
       <li class="nav-item text-nowrap">
       <form id="logoutForm" method="post" action="<%=request.getContextPath() %>/login/logout"></form>
         <a class="nav-link" href="javascript:logoutForm.submit();">Sign out</a> <!-- .submit() = 이벤트는 발생시키지 않겠다는 뜻 -->
       </li>
        <%
     }
  %>
  </ul>
</nav>