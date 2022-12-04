<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div style="height: 70px"></div>
<div class="row col-md-12 justify-content-center mb-2 mt-5 mb-3"
	style="margin-top: 100;">
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="likeSelect">
			<option value="">관심지역 선택</option>
<%-- 			<c:forEach items="${list}" var="like">
				<option> ${like.sidoName} ${like.gugunName} ${like.dongName} &nbsp;</option>
			</c:forEach>
 --%>		</select>
	</div>
	<div class="form-group col-md-2">
		<button id="apt_like" type="button" class="btn btn-primary"
			onclick="" style="width: 190px;">아파트 조회 하기</button>
	</div>
</div>

<script src="${root}/assets/js/like.js"></script>
