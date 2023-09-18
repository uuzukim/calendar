<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
${message }
<form method="post"> <!--  여기 액션이 없다는것? 저장버튼을 누르면 insertcontroller를 타고온다는것?--> 
	<table>
<!-- 		<tr> -->
<!-- 			<th>회원아이디</th> -->
<!-- 			<td><input class="form-control" type="text" name="memId" -->
<%-- 				value="<%=member.getMemId()%>" /> <span class="error"><%=errors.get("memId")%></span> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<form method="post">
   <table>
      <tr>
         <th>회원아이디</th>
         <td><input class="form-control" type="text" name="memId"
            value="${member.memId }" /><span class="error">${errors["memId"]}</span></td>
      </tr>
      <tr>
         <th>비밀번호</th>
         <td><input class="form-control" type="text" name="memPass"
            value="${member.memPass }" /><span class="error">${errors["memPass"]}</span></td>
      </tr>
      <tr>
         <th>회원명</th>
         <td><input class="form-control" type="text" name="memName"
            value="${member.memName }" /><span class="error">${errors["memName"]}</span></td>
      </tr>
      <tr>
         <th>주민번호1</th>
         <td><input class="form-control" type="text" name="memRegno1"
            value="${member.memRegno1 }" /><span class="error">${errors["memRegno1"]}</span></td>
      </tr>
      <tr>
         <th>주민번호2</th>
         <td><input class="form-control" type="text" name="memRegno2"
            value="${member.memRegno2 }" /><span class="error">${errors["memRegno2"]}</span></td>
      </tr>
      <tr>
         <th>생일</th>
         <td><input class="form-control" type="datetime-local" name="memBir"
            value="${member.memBir }" /><span class="error">${errors["memBir"]}</span></td>
      </tr>
      <tr>
         <th>우편번호</th>
         <td><input class="form-control" type="text" name="memZip"
            value="${member.memZip }" /><span class="error">${errors["memZip"]}</span></td>
      </tr>
      <tr>
         <th>주소1</th>
         <td><input class="form-control" type="text" name="memAdd1"
            value="${member.memAdd1 }" /><span class="error">${errors["memAdd1"]}</span></td>
      </tr>
      <tr>
         <th>주소2</th>
         <td><input class="form-control" type="text" name="memAdd2"
            value="${member.memAdd2 }" /><span class="error">${errors["memAdd2"]}</span></td>
      </tr>
      <tr>
         <th>집전번</th>
         <td><input class="form-control" type="text" name="memHometel"
            value="${member.memHometel }" /><span class="error">${errors["memHometel"]}</span></td>
      </tr>
      <tr>
         <th>회사전번</th>
         <td><input class="form-control" type="text" name="memComtel"
            value="${member.memComtel }" /><span class="error">${errors["memComtel"]}</span></td>
      </tr>
      <tr>
         <th>휴대폰</th>
         <td><input class="form-control" type="text" name="memHp"
            value="${member.memHp }" /><span class="error">${errors["memHp"]}</span></td>
      </tr>
      <tr>
         <th>이메일</th>
         <td><input class="form-control" type="text" name="memMail"
            value="${member.memMail }" /><span class="error">${errors["memMail"]}</span></td>
      </tr>
      <tr>
         <th>직업</th>
         <td><input class="form-control" type="text" name="memJob"
            value="${member.memJob }" /><span class="error">${errors["memJob"]}</span></td>
      </tr>
      <tr>
         <th>취미</th>
         <td><input class="form-control" type="text" name="memLike"
            value="${member.memLike }" /><span class="error">${errors["memLike"]}</span></td>
      </tr>
      <tr>
         <th>기념일</th>
         <td><input class="form-control" type="text" name="memMemorial"
            value="${member.memMemorial }" /><span class="error">${errors["memMemorial"]}</span></td>
      </tr>
      <tr>
         <th>기념일자</th>
         <td><input class="form-control" type="date"
            name="memMemorialday" value="${member.memMemorialday }" /><span
            class="error">${errors["memMemorialday"]}</span></td>
      </tr>
      <tr>
         <th>마일리지</th>
         <td><input class="form-control" type="number" name="memMileage"
            value="${member.memMileage }" /><span class="error">${errors["memMileage"]}</span></td>
      </tr>
      <tr>
         <th>탈퇴여부</th>
         <td><input class="form-control" type="text" name="memDelete"
            value="${member.memDelete }" /><span class="error">${errors["memDelete"]}</span></td>
      </tr>
      
      <tr>
         <td colspan="2">
         <input type="submit" value="저장"> 
         <input type="reset" value="취소">
         </td>
      </tr>
      
   </table>
</form>
