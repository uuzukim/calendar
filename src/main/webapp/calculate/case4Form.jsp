<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.List"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Arrays"%>
<%@page import="kr.or.ddit.enumpkg.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/customLibs.js"></script>
</head>
<body>
<div style="border:1px solid black;">
	<h4>request content-type header : server side unmarshalling 여부</h4>
	<input type="radio" name="contentType" value="Parameter" checked /> Parameter
	<input type="radio" name="contentType" value="json" /> JSON
</div>
<form id="calForm" action ="<%=request.getContextPath() %>/calculate/Case4ProcessServlet" method="post">
	<input type="number" name="leftOp" />
	<select name="opParam">
		<option value>연산자</option>
		<%
			OperatorType[] opTypes = OperatorType.values();
			String options = Arrays.stream(opTypes)
							.map(op->MessageFormat.format("<option value =''{0}''>{1}</option>", op.name(),op.getSign()))
							.collect(Collectors.joining("\n"));
			out.println(options);
		%>
	</select>
	<input type="number" name="rightOp" />
	<button type="submit">=</button>
</form>
<div id = "resultArea">

</div>
<script type="text/javascript">

// 폼이 제출되었을 때 AJAX를 사용하여 서버에 데이터를 전송하는 기능구현. 
// 폼데이터를 직렬화하고 설정된 AJAX요청을 보내어 서버의 응답을 처리하는 역활. 



$(calForm).on("submit", (event)=>{
	event.preventDefault();
	let calForm = event.target; // calForm을 말함
	let url = calForm.action;
	let method = calForm.method;
	
	
	let data = $(calForm).serialize();// 넘어가는 값이 파라미터라는뜻.
	
	let settings={
			url: url,
			method:method,

			dataType:"json", // json(application/json), xml(application/xml), html(text/html), text(text/plain)	
							// Accept(request header), Content-Type(response header)
			success:function(resp){// 데이터타입이 json이면 resp도 json타입. 
				//resultArea.innerHTML = `<p>\${resp.expr}</p>`;		
				$(resultArea).html($("<p>").html(resp['expr']) );// 위에랑 같은 방식  
			},
			error: function(jqXHR, status, error){
				console.log(jqXHR)
				console.log(status)
				console.log(error)
			}
	} //request line, header, body -> response processing
	
	let contentType = $("[name=contentType]:checked").val() ?? "parameter";
	
	if(contentType.toLowerCase()=="json"){//toLowerCase() 대소문자 신경안써도됨
		settings.contentType = "application/json;charset=utf-8";
		let nativeData = $(calForm).serializeObject();//폼 데이터를 javascript객체로 직렬화한 결과를 nativeData에 담는다. 
		settings.data = JSON.stringify(nativeData);
		//json은 마샬린과 언마샬린함수를 만든다. 
	}else{
		settings.data = $(calForm).serialize();
	}
	
	$.ajax(settings);
});

</script>

</body>
</html>