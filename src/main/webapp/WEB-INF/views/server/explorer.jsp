<%@page import="kr.or.ddit.servlet05.FileAdapter"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
/* 	li.dir: hover{ */
/* 		cursor : pointer; */
/* 	} */
		
	
</style>
<%
	List<FileAdapter> listFiles = (List) request.getAttribute("listFiles");
%>
<h4><%=application.getRealPath("/") %></h4>
<ul id="explorer">
	<%
// 		Wrapper[Adapter] design pattern		
		String contextRealPath = application.getRealPath("/");
	
		for(FileAdapter tmp : listFiles){
			String clz = tmp.getClzValue();
//			String clz = tmp.isDirectory() ? "dir" : "file";
			String logicalPath = tmp.getLogicalPath();
			//String logicalPath = tmp.getCanonicalPath()
			//					.substring(contextRealPath.length()-1)
			//					.replace(File.separator, "/");
			%>
			<li class="<%=clz%>" data-target="<%= logicalPath%>" data-length="<%=tmp.length() %>" data-mime-type="<%=tmp.getMimeType()%>"><%=tmp.getName() %></li>
			<%
		}
	%>
</ul >
<div id="fileInfoArea">
	파일명 : 1.0Mb, image/jpeg 
</div>
<script>
	$(explorer).on("click", "li.dir", function(event){ // "li.dir"디센던트 자식구조
		let target = event.target.dataset["target"];
		if(target)
			location.href="?target="+target;
	}).on("click","li.file", function(event){
		let settings={
				url: "<%=request.getContextPath()%>/server/fileInfo",
				//method:"get", get메소드는 생략가능
				dataType:"json", // Accept:application/json / content-Type이랑 한쌍 . contentType은 view에서 설정이된다. 그래서 이건 컨트롤러와 연관된게 아니라 view랑 연관있는것.	
				//contentType : "application/json", // 얘는 req의 content 그래서 얘는 컨트롤러와 연관된것. 
				data:{
					target : event.target.dataset.target
				},
				success:function(resp){
					let fileInfo = resp.fileInfo;	
					fileInfoArea.innerHTML = `\${fileInfo.name} : \${fileInfo.fancysize} ,\${fileInfo.mimeType}  `
				},
				error: function(jqXHR, status, error){
						console.log(jqXHR)
						console.log(status)
						console.log(error)
					}
		} //request line, header, body -> response processing
		$.ajax(settings);		
	}).find("li.dir").css("cursor", "pointer");
</script>
