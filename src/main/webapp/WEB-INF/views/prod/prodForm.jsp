<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form method="post">
	<input type="hidden" name="prodId" value="${prod.prodId}"/>
	
	<table class="table table-border">
	       <tr>
         <th>상품명</th>
         <td>
            <input class="form-control" type="text" name="prodName" 
               value="${prod.prodName }" />
            <span class="error">${errors["prodName"]}</span>
         </td>
      </tr>
      <tr>
         <th>상품분류</th>
         <td>
            <select name="prodLgu">
               <option value>분류선택</option>
               <c:forEach items="${lprodList }" var="lprod">
                  <option value="${lprod.lprodGu }">${lprod.lprodNm }</option>
               </c:forEach>
            </select>
         </td>
      </tr>
      <tr>
         <th>거래처</th>
         <td>
            <select name="prodBuyer">
               <option value>거래처선택</option>
               <c:forEach items="${buyerList }" var="buyer">
                  <option value="${buyer.buyerId }" class="${buyer.buyerLgu }">${buyer.buyerName }</option>
               </c:forEach>
            </select>
            <span class="error">${errors["prodBuyer"]}</span>
         </td>
      </tr>
		<tr>
			<th>구매가</th>
			<td><input class="form-control" type="number" name="prodCost"
				value="${prod.prodCost }" /><span class="error">${errors["prodCost"]}</span></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><input class="form-control" type="number" name="prodPrice"
				value="${prod.prodPrice }" /><span class="error">${errors["prodPrice"]}</span></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><input class="form-control" type="number" name="prodSale"
				value="${prod.prodSale }" /><span class="error">${errors["prodSale"]}</span></td>
		</tr>
		<tr>
			<th>요약정보</th>
			<td><input class="form-control" type="text" name="prodOutline"
				value="${prod.prodOutline }" /><span class="error">${errors["prodOutline"]}</span></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><input class="form-control" type="text" name="prodDetail"
				value="${prod.prodDetail }" /><span class="error">${errors["prodDetail"]}</span></td>
		</tr>
		<tr>
			<th>이미지경로</th>
			<td><input class="form-control" type="text" name="prodImg"
				value="${prod.prodImg }" /><span class="error">${errors["prodImg"]}</span></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><input class="form-control" type="number"
				name="prodTotalstock" value="${prod.prodTotalstock }" /><span
				class="error">${errors["prodTotalstock"]}</span></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><input class="form-control" type="date" name="prodInsdate"
				value="${prod.prodInsdate }" /><span class="error">${errors["prodInsdate"]}</span></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><input class="form-control" type="number"
				name="prodProperstock" value="${prod.prodProperstock }" /><span
				class="error">${errors["prodProperstock"]}</span></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><input class="form-control" type="text" name="prodSize"
				value="${prod.prodSize }" /><span class="error">${errors["prodSize"]}</span></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><input class="form-control" type="text" name="prodColor"
				value="${prod.prodColor }" /><span class="error">${errors["prodColor"]}</span></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><input class="form-control" type="text" name="prodDelivery"
				value="${prod.prodDelivery }" /><span class="error">${errors["prodDelivery"]}</span></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><input class="form-control" type="text" name="prodUnit"
				value="${prod.prodUnit }" /><span class="error">${errors["prodUnit"]}</span></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><input class="form-control" type="number" name="prodQtyin"
				value="${prod.prodQtyin }" /><span class="error">${errors["prodQtyin"]}</span></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><input class="form-control" type="number" name="prodQtysale"
				value="${prod.prodQtysale }" /><span class="error">${errors["prodQtysale"]}</span></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><input class="form-control" type="number" name="prodMileage"
				value="${prod.prodMileage }" /><span class="error">${errors["prodMileage"]}</span></td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="submit" value="저장">
			<input type="reset" value="취소">
		</tr>
	</table>
</form>

<script>

	let $prodBuyer = $('[name="prodBuyer"]').val("${prod.prodBuyer}");
	$('[name="prodLgu"]').on("change", function(event){
		let lprodGu = $(this).val();
		if(lprodGu){
			$prodBuyer.find("option").hide();
			$prodBuyer.find(`option:first`).show();
			$prodBuyer.find(`option.\${lprodGu}`).show();
		}else{
			$prodBuyer.find("option").show();
		}
	}).val("${prod.prodLgu}")
		.trigger("change");// trigger 강제로 실행하게 하는 함수
</script>