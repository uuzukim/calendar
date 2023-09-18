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
<span>이페이지는 비동기 방식으로 데이터를 보내기 위한 페이지이다. 여기서 마샬링, 직렬화까지 하고 보낸다!</span>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/customLibs.js"></script>
</head>
<body>
<div style="border:1px solid black;">
	<h4>request content-type header : server side unmarshalling 여부</h4>
	<input type="radio" name="contentType" value="Parameter" checked /> Parameter
	<input type="radio" name="contentType" value="json" /> JSON
</div>
<div style="border: 1px solid black">
	<h4>request accept header == response content-type : server side marshalling 여부 </h4>
	<input type="radio" name="acceptType" value="json" />JSON
	<input type="radio" name="acceptType" value="xml" />XML
	<input type="radio" name="acceptType" value="html" />HTML
</div>

<form id="calForm" action ="<%=request.getContextPath() %>/calculate/Case5ProcessServlet" method="post">
	<input type="number" name="leftOp" />
	<select name="opParam">
		<option value>연산자</option>
		<%
			OperatorType[] opTypes = OperatorType.values();
			String options = Arrays.stream(opTypes)
							.map(op->MessageFormat.format("<option value =''{0}''>{1}</option>", op.name(),op.getSign()))
							.collect(Collectors.joining("\n")); //  이건 enum클래스에서 이름과 sign가져오는 메소드 
			out.println(options); // 그럼 뭐가 가져오게되나?"<option value =''{0}''>{1}</option><option value =''{0}''>{1}</option><option value =''{0}''>{1}</option>" 이렇게 옴
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
			let $newTag = $("<p>").html(resp.expr); // 여기엔 그럼 enum 클래스의 operatorType의 expression의 2 * 2 = 4 이런식으로 오게된다. 
			$resultArea.html($newTag);
		},
		html:function(resp){ //html 소스
			let $newTag = $("<p>").html(resp); // 객체가 아니라서 dot.nataion 사용불가! 그리고 html이기 때문에 그냥 받아와서 출력하면 됨. 
			$resultArea.html($newTag);		
		}
	}

$(calForm).on("submit", (event)=>{
	event.preventDefault();
	let calForm = event.target; // calForm을 말함 이벤트 걸리는 타겟. target= this같은 느낌임... 
	let url = calForm.action; // 그냥 있는말
	let method = calForm.method; // 그냥 있는말 
	
	let data = null;
	let headers = {};
	
	let dataType = $("input[name=acceptType]:checked").val() ?? "html"; // 데이터타입 동적으로 받기 
//		dataType ? dataType : "html" =>dataType ?? "html" // 위에 input체크가 둘다 안되어 있을때 기본 값 필요하겠지? 그래서 설정. 	
// acceptyType값이 설정 되어있으면 그 값을 쓰고 없으면?? html로 해라 . 
	
	let contentType = $("[name=contentType]:checked").val() ?? "parameter";
	if(contentType.toLowerCase()=="json"){// LowerCase : 대문자 소문자로 변환작업
		let nativeData = $(calForm).serializeObject();// 폼데이터를 자바스크립트 객체로 직렬화한다. 이때 serializeObject()함수를 사용하며 , 
													 //이 함수는 폼 데이터를 자바스크립트 객체로 변환하는 역활을 한다. 
		//얘가 마샬링
		data = JSON.stringify(nativeData); // nativeData객체를 json문자열로 변환하여 data변수에 저장한다. 
		headers['Content-Type'] = "application/json;charset=UTF-8";
		
	}else{
		data = $(calForm).serialize();//
	}
	
	let success = functions[dataType];
	
	let settings={
			url: url,
			method:method,
			data: data,
			headers : headers,
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