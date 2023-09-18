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
<title>case3은 응답데이터를 다양하게 함.</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ></script>
</head>
<body>
<div style="border: 1px solid black">
	<h4>request accept header == response content-type : server side marshalling 여부 </h4>
	<input type="radio" name="acceptType" value="json" />JSON
	<input type="radio" name="acceptType" value="xml" />XML
	<input type="radio" name="acceptType" value="html" />HTML
</div>

<form id="calForm" action ="<%=request.getContextPath() %>/calculate/Case3ProcessServlet" method="post">
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
let $resultArea = $('#resultArea');
let functions = {
		json:function(resp){//언마샬링전 객체
			let $newTag = $("<p>").html(resp.expr);
			$resultArea.html($newTag);
		},
		html:function(resp){ //html 소스
			let $newTag = $("<p>").html(resp); // 객체가 아니라서 dot.nataion 사용불가! 
			$resultArea.html($newTag);		}
}

$(calForm).on("submit", (event)=>{
	event.preventDefault();
	let calForm = event.target; // calForm을 말함
	let url = calForm.action;
	let method = calForm.method;
	let data = $(calForm).serialize();
	let dataType = $("input[name=acceptType]:checked").val() ?? "html"; // 데이터타입 동적으로 받기 
//		dataType ? dataType : "html" =>dataType ?? "html" // 위에 input체크가 둘다 안되어 있을때 기본 값 필요하겠지? 그래서 설정. 	
// acceptyType값이 설정 되어있으면 그 값을 쓰고 없으면?? html로 해라 . 
		
	let success = functions[dataType];
	
	let settings={
			url: url,
			method:method,
			data: data,
			dataType:dataType, // json(application/json), xml(application/xml), html(text/html), text(text/plain)	
							// Accept(request header), Content-Type(response header)
					
			success: success,
			error: function(jqXHR, status, error){
				console.log(jqXHR)
				console.log(status)
				console.log(error)
			}
	} //request line, header, body -> response processing
	$.ajax(settings);
});

</script>
</body>
</html>