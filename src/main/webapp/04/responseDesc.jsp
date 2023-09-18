<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	response.setContentLength(10); // 편지지의 내용이 10바이트로 구성된다(5글자)
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/responseDesc.jsp</title>
</head>
<body>
<h4>HttpServletResponse</h4>
<pre>
	: 서버가 클라이언트로 전송하는 응답에 대한 모든 정보를 가진 객체.
	
	HTTP response packaging
	1. Response Line : 여기에는 어떤정보가 있어야 할까? -> 서버의 요청 처리 결과를 표현하는 상태 코드 (response status)
						이걸 왜 숫자로 표현할까? 사용자는 사람이지만 프로그램이 쉽게 이해하기 위해 숫자로 표현 .
						*response status code *
						HTTP : connectLess(의도적으로 수립하고 끊는게 아님.제어권한이 나한테 없음)
							   stateLess
						100~ : (되게 애매한 녀석임. 아직은 너의 요청처리가 끝나지 않았어. 기다려쥬~~ ) 
								보통 브라우저는 0.2초로 응답이 나타나는데 1초가 걸려 . 그럼 계속 기다리라고 얘기해줘야 하잖아. 그때 사용.
								(101)ing..., connectfull (의도적으로 연결을 수립/종료가 가능.),Websocket protocol 에서 활용됨. 
								
						200~ : 성공 OK(SUCCESS)
						
						300~ : 최종적으로 요청 처리가 완료 되려면, client로 부터 추가 액션이 필요함.즉, 아직 요청이 끝나지 않은 상태. 
								특징은 response에 body가 없음. 
							304(Not Modified) : 수정되지 않았음. 정적자원의 cache와 연관된 상태코드. 
							302/307(Moved) : 예를들어 저번주에는 정솔이 앉아있었는데 송빈이가 앉았어. 그럼 responsebody가 없어야겠지?
											그리고 나는 정솔이 아닙니다를 얘기해주고, 정솔의 자리가 어디인지 알려주겠찌 어디로? location header로 
											Location 헤더를 통해 자원의 새로운 위치를 알려줌. 
											==> Location 방향으로 새로운 요청을 전송함. (redirect 이동 구조) 
											
						400~ : (클라이언트에게 잘못이 있음 bad request)client side error
							400 : (요청 자체가 잘못되었을때 , 요청 검증시 사용.) Bad Request
							401/403 : 보안(인증-authentication/인가-authorization) 처리에 활용 
								401(UnAuthrorized) : 미인증 사용자에 대해 사용(UnAuthenticated).
								403(Forbidden) : 특정 자원에 대한 접근 허용 여부에 사용. 권한이 없는 사용자의 접근을 막을때 사용함.								
												: 보호자원에도 보호 등급이 있다. ex)모든 회원정보는 최상위보호등급설정해야함. / 권한체크할때 사용.
							404 : (주소를 잘못 적었을 때) Not Found 
							405 : (서버에서 dopost방식으로 요청했는데 클라이언트가 doget방식으로 전달하려할때)Method Not Allowed
							406/415 : 전송 컨텐츠 타입과 연관된 상태코드 
								406(Not Acceptable) : (해당 서버에서 이 컨탠츠는 만들수 없어! )request Accept 헤더와 연관됨. 
													: 서버가 클라이언트의 요청 컨텐츠 타입을 처리할 수 없음. 
													: accept헤더는 바디에 뭘 표현할지 담는다.(json? html?)
								415(UnSupported Media Type) : request Content-Type헤더와 연관됨.
															: 서버가 읽을 수 없는 형태의 컨텐츠가 전송되었을때.
															: 클라이언트가 body에 json형태로 보냈어 그럼 content에 그렇게 담겨잇겠찌? 
															: 근데 내가 un마샬링 할 능력이 없으면 body를 읽을 수 없겠지? 그럴떄 나오는 오류코드.
															: 415는 body랑 연관이 있고 406는 accept랑 연관이 있다.   			
						500~ : (오타 이런거..)server side error
							500 : (하나로 퉁침 왜? 보안적인 측면에서 자세히 알려주면 안되겠지?)Internal Server Error
							
	2. Response Header : response body의 메타데이터, name/value
		* Content- *(Content-Type, Content-Length)
		* Cache-*
		* auto request (Refresh - 갱신 주기, 초단위)
			:	동기 요청구조상에서 document 에 대한 제어권이 필요함.
			cf) JS 비동기 요청 구조에서 auto request : JS 스케쥴링 함수 활용 가능.
		* reirect Location *
	3. Response Body(Content Body, Message Body)
		1) JSP의 정적 텍스트
		2) JSPWriter out 객체 이용
		3) PrintWriter response.getWriter() 객체 이용
		4) ServletOutputStream response.getOutputStream() 객체 이용 
		
		===> response body 데이터를 직렬화.
	<%--
		// response body 쓰는 방법
		out.println("text");
		response.getWriter().println("text");
		response.getOutputStream().write(4);
	
	--%>
</pre>
</body>
</html>