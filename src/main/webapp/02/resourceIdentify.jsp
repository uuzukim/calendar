<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 자원의 종류와 식별 방법 </h4>
<pre>
	1. file system resource
	2. classpath resource
	☆3. web resource
		: 자원을 식별하는 방법 
		URI (Uniform Resource Identifier_식별자)
		URL (Uniform Resource Locator_위치)
		URN (Uniform Resource Naming_이름)
		URC (Unifrom Resource Content_내용)
		//urn, urc도태된이유 //
		urn - 정솔씨 일어나세요. 반에 정솔이 2명이다? 그럼 식별 어려움
		urc - 책을 검색하는데 내용이 중복되는게 있으면 식별이 어렵잖아?
		url도 단점이 있다. 
		url- 한달에 한번 자리가 바뀌었어. 정솔자리에 김유주가 왔어 . 유주가 할일은?
 		저는 정솔이 아닙니다. 정솔의 자리는 어디입니다. 이걸하는거 redirect.우리 중프때 많이 썼던거
		
		
		URL 구성요소 
		http:// www.naver.com/depth01/....
		protocol:// IP[domain]:port/depth.....
		
		절대경로 " / " 로 시작  
			1)"http://localhost:80/Webstudy01/resources/images/cat1.jpg"
			2)"//localhost:80/Webstudy01/resources/images/cat1.jpg" (location.protocol갖고 있기 때문에 http생략가능)
			3)"/Webstudy01/resources/images/cat1.jpg" (location.host, location.port 갖고 있기 때문에 생략가능)
			
		상대경로 : 현재 위치(location.href)를 기준으로 절대 경로를 파악하는 방법. 
		
		 
		
</pre>
<img src="../resources/images/cat1.jpg" />
<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg" />
<img src="WebStudy01/resources/images/cat1.jpg" />
<img src="//localhost:80/WebStudy01/resources/images/cat1.jpg" />
<img src="http://localhost:80/WebStudy01/resources/images/cat1.jpg" />
</body>
</html>