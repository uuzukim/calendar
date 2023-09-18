<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>Flow Control (흐름 제어, 페이지 이동방식, A->B)</h4>
<form id="alterForm" method="post" action="../07/start.jsp">
	<input type="text" name="param" value="PARAMVALUE" />
</form>
<h4><a href="javascript:alterForm.submit();">start.jsp(A) 를 대상으로 요청 발생</a></h4>
<pre>
	1. Request Dispatch 방식 : 서버사이드 이동방식(서버사이드 위임구조)
							: 기존의 요청을 가지고 서버 내에서만 분기하는 구조. 
							: 
		- forward(Model 2 구조 활용) : A의 요청을 가지고 B로 분기한 후, 최종 응답이 B에서만 전송되는 구조. 
					A : request 담당(Controller layer).
					B : response 담당(View layer).
		- include(Page Modulization 구조 활용) : A의 요청을 가지고 B로 분기한 후, B에서 만들어진 데이터를 가지고 A로 복귀하는 구조. 
					--> 최종 응답은 A+B의 형태로 전송됨. 
					Page Modulization : 하나의 응답 페이지를 여러개의 JSP로 분리하여 완성하는 구조(페이지구조화)
					
		<%--
			String path = "/02/standard.jsp" ; // 얘는 서버가 사용하는 주소이기 때문에 앞에  contextpath가 없어도 사용가능하지만
												아래 location 에는 클라이언트(웹)에서 사용하기 때문에 contextpath가 없으면 찾지못함.
			RequestDispatcher rd = request.getRequestDispatcher(path);
			//rd.forward(request, response);
			rd.include(request, response);
		--%>
	Http : ConnectLESS, StateLess 연결, 상태 유지 안된다. 				
	2. Location 헤더를 바탕으로 redirect
			: A에서 body가 없고, 상태코드(302)와 Location 헤더를 가지고 response가 전송(request 정보 삭제됨)
			클라이언트는 Location 헤더의 B 주소를 대상으로 완전히 새로운 요청을 전송함 
			--> 최종적으로 B에서 완전한 body가 있고, 정상처리 된 경우, 200상태코드로 response가 전송됨. 
		<%--
			String location = request.getContextPath()+"/02/standard.jsp" ;
			response.sendRedirect(location); // 이 location은 클라이언트(웹)가 사용하는 주소 
			// 이렇게 하면 요청이 네트워크에 flowControl.jsp와 standard.jsp가 뜬다! 
			// 결과적으로 뜨는 것은 standard.jsp
		--%>
</pre>
</body>
</html>