<%@page import="java.util.stream.Collectors"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// bloodTypeServlet에서 값 가져오기
	Map <String, String[]> bloodType = (Map) application.getAttribute("bloodType");
%> 
<h4>혈액형별 성격 유형 검사</h4>
<form method="post">
	<select class="form-select" name="blood" onchange="this.form.requestSubmit();">
<!-- 	onchange="this.form.requestSubmit();"submit 이벤트를 발생시키고 싶음. 여기서 this.는 타겟 이벤트를 말함  -->
		<option value>혈액형</option>
		<%= // 옵션이 만들어짐
			// 가져온 값 
			bloodType.entrySet().stream()
				.map(e->MessageFormat.format("<option value=''{0}''>{1}></option>" //{0}에는 b001, {1}에는 a형
										,e.getKey(), e.getValue()[0]))
				.collect(Collectors.joining("\n"))
		%>
	</select>
</form>
<script>
	console.log($.ajax);
</script>