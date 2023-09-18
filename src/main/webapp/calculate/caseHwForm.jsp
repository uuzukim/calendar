<%@page import="java.text.MessageFormat"%>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ><</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/customLibs.js"></script>
</head>
<body>
<div style = "border:1px solid black;">
	<a>요청컨텐츠</a>
	<input type="radio" name="contentType" value="Parameter" checked /> Parameter
	<input type="radio" name="contentType" value="json" /> JSON
</div>
<div style="border: 1px solid black">
	<a>응답컨텐츠</a>
	<input type="radio" name="acceptType" value="json" checked />JSON
	<input type="radio" name="acceptType" value="html" />HTML
</div>
<form id="calForm" action="<%=request.getContextPath() %>/calculate/CaseHwProcessServlet" method="post">
	<input type="number" name="leftOp" />
	<select name="opParam">
		<option value>연산자</option>
		<%
			OperatorType[] opTypes = OperatorType.values();
			String options = Arrays.stream(opTypes)
							.map(op->MessageFormat.format("<option value=''{0}''>{1}</option>", op.name(),op.getSign()))
							.collect(Collectors.joining("\n"));
			out.println(options);
		%>
	</select>
	<input type="number" name="rightOp" />
	<button type="submit">=</button>
</form>
<div id="resultArea">

</div>

<script type="text/javascript">
// 응답컨텐츠
$(calForm).on("submit", (event)=>{
	event.preventDefault();
	let calForm = event.target; // calForm을 말함
	let url = calForm.action;
	let method= calForm.method;
	
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
		let nativeData = $(calForm).serializeObject();
		settings.data = JSON.stringify(nativeData);
	}else{
		settings.data = $(calForm).serialize();
	}
	
	$.ajax(settings);
});	

// 요청컨텐츠
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