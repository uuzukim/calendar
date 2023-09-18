<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-border">
    <thead>
        <tr>
            <th>회원아이디</th>
            <th>회원명</th>
            <th>휴대폰</th>
            <th>이메일</th>
            <th>생일</th>
            <th>거주지역(add1)</th>
            <th>마일리지</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${empty memberList}">
                <tr>
                    <td colspan="7">회원정보가 존재하지 않습니다.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="member" items="${memberList}">
                	
                    <tr>
                        <td>${member.memId}</td>
                        <td><a href="javascript:;" data-who="${member.memId }" data-bs-toggle="modal" data-bs-target="#exampleModal">${member.memName }</a></td>
                        <td>${member.memHp}</td>
                        <td>${member.memMail}</td>
                        <td>${member.memBir}</td>
                        <td>${member.memAdd1}</td>
                        <td>${member.memMileage}</td>
                    </tr>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

<c:url value="/member/memberView.do" var="viewURL" />

<!-- Modal -->
<div class="modal fade" data-url="${viewURL }"id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

<script src="<c:url value='/resources/js/app/member/memberList.js' />"></script>