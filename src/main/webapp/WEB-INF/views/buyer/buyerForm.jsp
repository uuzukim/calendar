<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post">
	<input type="hidden" name="buyerId" value="${buyer.buyerId }" />
	<table class="table table-border">
		
		<tr>
			<th>거래처명</th>
			<td><input class="form-control" type="text" name="buyerName"
				value="${buyer.buyerName }" /><span class="error">${errors["buyerName"]}</span></td>
		</tr>
		<tr>
			<th>분류명</th>
				<td>
				  <select name="buyerLgu" class="form-select">
				    <option value="">분류선택</option>
				    <c:forEach items="${lprodList}" var="lprod">
				      <option value="${lprod.lprodGu}">${lprod.lprodNm}</option>
				    </c:forEach>
				  </select>
				</td>
		</tr>
		<tr>
			<th>거래처은행</th>
			<td><input class="form-control" type="text" name="buyerBank"
				value="${buyer.buyerBank }" /><span class="error">${errors["buyerBank"]}</span></td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td><input class="form-control" type="text" name="buyerBankno"
				value="${buyer.buyerBankno }" /><span class="error">${errors["buyerBankno"]}</span></td>
		</tr>
		<tr>
			<th>예금주</th>
			<td><input class="form-control" type="text" name="buyerBankname"
				value="${buyer.buyerBankname }" /><span class="error">${errors["buyerBankname"]}</span></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><input class="form-control" type="text" name="buyerZip"
				value="${buyer.buyerZip }" /><span class="error">${errors["buyerZip"]}</span></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input class="form-control" type="text" name="buyerAdd1"
				value="${buyer.buyerAdd1 }" /><span class="error">${errors["buyerAdd1"]}</span></td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td><input class="form-control" type="text" name="buyerAdd2"
				value="${buyer.buyerAdd2 }" /><span class="error">${errors["buyerAdd2"]}</span></td>
		</tr>
		<tr>
			<th>연락처</th>
			<td><input class="form-control" type="text" name="buyerComtel"
				value="${buyer.buyerComtel }" /><span class="error">${errors["buyerComtel"]}</span></td>
		</tr>
		<tr>
			<th>팩스</th>
			<td><input class="form-control" type="text" name="buyerFax"
				value="${buyer.buyerFax }" /><span class="error">${errors["buyerFax"]}</span></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input class="form-control" type="text" name="buyerMail"
				value="${buyer.buyerMail }" /><span class="error">${errors["buyerMail"]}</span></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td><input class="form-control" type="text" name="buyerCharger"
				value="${buyer.buyerCharger }" /><span class="error">${errors["buyerCharger"]}</span></td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td><input class="form-control" type="text" name="buyerTelext"
				value="${buyer.buyerTelext }" /><span class="error">${errors["buyerTelext"]}</span></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장">
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>
</form>


