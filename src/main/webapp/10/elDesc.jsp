<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/elDesc.jsp</title>
</head>
<body>
<h4 id="titleElement" data-name="H4VALUE">EL(Expression Language)</h4>
<pre>
	: 속성 데이터 출력을 목적으로 하는 스크립트 형태 언어.
	
	1. EL 연산자
		1) 산술연산자 : + - * / %
			${4 / 3 } 동적타입 언어에서는 피연산자의 타입이 중요하지 않고 연산자가 메인이 된다. (자바스크립트랑 유사함.)
			그래서 나누기 연산자를 한다? 그럼 실수냐 정수냐 중요하지 않아. 그래서 나누기 연산자는 무조건 실수. 
			${"4"+"3" }=>"7", ${"4"+3 }=>7,${a+b }=>0
			&nbsp;, &lt;
		2) 논리연산자 : &&(and), ||(or), !(not)
					${true and false }, ${ture or false }, ${not true }
		3) 비교연산자 : == (eq), !=(ne), >(gt), >=(ge), <(lt), <=(le)
					${4 ge 3 }, ${"4" eq 4 }
		4) 삼항연산자 조건식 ? 참표현 : 거짓표현
			${4 ge 3 ? "크다" :"작다" } 
		5) ***단항연산자 : empty (비어있는지 여부 확인) 
			속성의 존재여부 -> null 여부 ->size(length)
			${empty asdf }=> 
	2. EL 객체의 멤버에 대한 접근법 : 점표기방식(dot notation), 연산배열방식
		속성명.멤버명,속성명['멤버명']
		
	3. EL 기본객체 
		1) scope : pageScope, requestScope, sessionScope, applicationScope
		2) param : 요청 파라미터를 가진 객체 
			${param.param1 }
		3) paramValues : 요청 파라미터를 가진 객체 
			${paramValues['param1'][0]}
		4) cookie내가만든쿠키ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ
			: 쿠키 객체들을 가진 객체 
			${cookie.idCookie.value}
			${cookie['idCookie']['value'] }
			${cookie['idCookie'].value }
		5) pageContext : 
			${pageContext.session.id }
			${cookie['JSESSIONID']['value'] }
			${pageContext.request.contextPath }, <%=request.getContextPath() %>
	
	
	<%-- 	<%=변수명, 값, 연산식 %>.--%> 
	<%-- ${속성명, 값, 연산식 }  --%>
	<%
		String tmp ="TEXT";
		request.setAttribute("tmp-2","요청"); 
		session.setAttribute("tmp-2","세션"); 
		Map<String, Object> attrMap = new HashMap<>();
		attrMap.put("key1", "value1");
		attrMap.put("key-2", "value2");
		attrMap.put("key4", new ArrayList());
		request.setAttribute("attrMap", attrMap);
	%>
	표현식 : <%=tmp %>, 
	EL : ${tmp2}, ${tmp }
		${requestScope['tmp-2'] }
		${sessionScope.tmp-2 }
		${attrMap.key1}
		${attrMap['key-2']}
		${empty attrMap.key4 }
</pre>
</body>
</html>