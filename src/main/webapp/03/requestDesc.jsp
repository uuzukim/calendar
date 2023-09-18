<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ></script>
</head>
<body>
<form id="sampleForm" action="<%=request.getContextPath() %>/03/dataProcess.do" method="post" >
<pre>
<!-- input태그에서 name를 생략하면 서버로 보낼 수 없음 , name을 가지고 있는 것을 form-data라고 할 수 있음-->
   <input type="text" name="param1" />
   <input type="number" name="param2" />
   <select name="selParamSingle">
      <option value="">선택</option>
      <option value="opt1Value">opt1</option>
      <option>opt2</option>
   </select>
   <!-- property는 boolean타입(?)
   attr(어트리뷰트)-> ("multiple","multiple")
   prop-> ("multiple", true) -->
   <select name="selParamMulti" multiple="multiple" size="5">
      <option value="">선택</option>
      <option value="opt1Value">opt1</option>
      <option>opt2</option>
   </select>
   <label><input type="radio" name="rdoParam" value="radio1"/>RADIO1</label>
   <label><input type="radio" name="rdoParam" value="radio2"/>RADIO2</label>
   <label><input type="checkbox" name="chkParam" value="Check1" />CHECK1</label>
   <label><input type="checkbox" name="chkParam" value="Check1"/>CHECK2</label>
   <input type="date" name="dateParam"/>
   <input type="datetime-local" name="datetiemParam"/>
   <input type="submit" value = "전송" />
</pre>
</form>
<h4>Http Request Packaging(HttpServletRequest)</h4>
<pre>
   1. Request Line : protocol/version(HTTP/1.1) URL, Request Method
      <%=request.getProtocol() %>
      <%=request.getRequestURI() %>
      <%=request.getMethod()%>
   
      * Request Method : 현재 요청의 의도(목적)과 패키징 구조를 표현
       1) GET(default), R : Read
       2) POST, C : Write
       3) PUT(전체수정)/PATCH(부분수정), U : Update
       4) DELETE, D
       5) OPTIONS : preFlight 요청에 사용됨.
       6) HEAD : response Body가 없는 응답을 요청함.
       7) TRACE : server debugging 요청, 관리 목적의 콘솔에서만 사용
       
   2. Request Header : meta data(현재 클라이언트와 요청에 대한 부가 정보)
      <%=request.getHeader("accept") %>
      Accept-*(로 시작하는 헤더) : 응답에 대한 조건을 설정.
      ex) Accept : text/html(응답이 html로 와야하는 것), application/json(응답이 json으로 와야하는 것)
      Content-*(로 시작하는 헤더) : request body에 대한 부가 정보, 요청에 대한 조건을 설정
      ex) Content-Type : multipart/form-data, application/json(클라이언트가 서버로 보내고 있는 것이 json이라는 것)
      User-Agent : 클라이언트가 사용하는 시스템과 브라우저에 대한 정보
      
   3. Request Body(Content Body, Message Body, only POST) : send data(content) 그 자체
   
      1) 요청 파라미터 (application/x-www-form-urlencoded)
         : Get 메소드에서도 Query String의 형태로 제한적인 파라미터를 전송할 수 있음
         - String getParameter(String parameterName) 사용
         - 인코딩 된 이후에 문자열로 전송되는 것이 특징
         - 이름과 값이 한 쌍(parameterName,String)
         - Map&lt;String, String[]&gt; getParameterMap() -> 이름이 같은 데이터가 있을 수 있기 때문에 String[]
         - 위에 것을 해결하기 위해 String[] getParameterValues(String parameterName) 아무리 하나의 데이터로 여러개 이름을 보내도 누락되지 않음
         - ENUMERATION&lt;String&gt; getParameterNames() 파라미터 이름만 가져옴
         
      2) multipart (multipart/form-data ->enctype에 의해 결정) : Part(servlet 3.x) , fileItem(servlet 2.x)
         - Part getPart(String partName)
         - Collection&lt;Part&gt; getParts()
         
      3) payload (json | xml, application/json | application/xml)
         - json | xml는 스트링 방식으로 바꿔야함
         - InputStream getInputStream()
           (역직렬화-DeSerialization, 언마샬링-UnMarshalling)
</pre>
<table>
   <thead>
      <tr>
         <th>헤더이름</th>
         <th>헤더값</th>
      </tr>
   </thead>
   <tbody>
      <%
         StringBuilder html = new StringBuilder();
         String pattern = "<tr><td>{0}</td><td>{1}</td></tr>\n";
         Enumeration<String> names = request.getHeaderNames();
         while(names.hasMoreElements()) {
            String headerName = names.nextElement();
            String headerValue = request.getHeader(headerName);
            html.append(
            MessageFormat.format(pattern, headerName, headerValue)
            );
         }
         out.println(html);
      %>
   </tbody>
</table>
<script type="text/javascript">
//   sampleForm.onsubmit = 
   $(sampleForm).on("submit", function(){
      event.preventDefault();
    //  this==event.target //HTMLElement
    //  $(this) // HTMLElement을  wrapper해서 만든 jquery Object
    //  this.action
    //  $(this).attr("action")
      console.log(`HtmlElement : \${this}`);
      console.log(`jquery Objent : \${$(this)}`);
      let url = this.action;
      let method = this.method;
      let data = $(this).serialize();
      let settings = {
         url : url,
         method : method,
         data : data,
         //---------------------------- 이 윗부분을 요청 구조, 요청방식을 정함 
         //---------------------------- 
         dataType : "json", // request Accept:application/json , response Content-type:application/json
         success : function(resp) {
		 	alert(resp.message);
         },
         error : function(jqXHR, status, error) {
            console.log(jqXHR)
            console.log(status)
            console.log(error)
         }
      } // request line, header, body -> response processing
      console.log(settings);
     $.ajax(settings);
      
      return false;
   }); //callback function);
</script>

</body>
</html>





