<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/sampleForm.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.7.0.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/customLibs.js"></script>
</head>
<body>
<form id="sampleForm" action="<%=request.getContextPath() %>/03/payloadDataProcess.do" method="post" >
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
   <label><input type="checkbox" name="chkParam" value="Check2"/>CHECK2</label>
   <input type="date" name="dateParam"/>
   <input type="datetime-local" name="datetiemParam"/>
   <input type="submit" value = "전송" />
</pre>
</form>
<script type="text/javascript">
    
//   sampleForm.onsubmit = 
   $(sampleForm).on("submit", function(){
      event.preventDefault();
      /* this==event.target //HTMLElement
      $(this) // HTMLElement을  wrapper해서 만든 jquery Object
      this.action 
      $(this).attr("action") */ 
      console.log(`HtmlElement : \${this}`);
      console.log(`jquery Object : \${$(this)}`);
      let url = this.action;
      let method = this.method;
      // 파라미터 전송시 사용.
//       let data = $(this).serialize(); // form 입력값들을 파라미터 형태로 전송가능한 문자열로 만들어줌.
//       this is HTMLElement
      let data = $(this).serializeObject();
      
      let settings = {
         url : url,
         method : method,
         data : JSON.stringify(data), //marshalling
         headers : {
            "Content-Type" : "application/json"
         },
         //------------------------
         dataType : "json", // request Accept:application/json, response Content-type:application/json
         
         success : function(resp) { //json을 받고 언마샬링 이후의 객체 들어오는 것
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