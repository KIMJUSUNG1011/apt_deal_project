<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div style="height: 70px"></div>
<div class="row col-md-12 justify-content-center mb-2 mt-5 mb-3">
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="sido">
			<option value="">시도선택</option>
		</select>
	</div>
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="gugun">
			<option value="">구군선택</option>
		</select>
	</div>
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="dong">
			<option value="">동선택</option>
		</select>
	</div>
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="year">
			<option value="">매매년도선택</option>
		</select>
	</div>
	<div class="form-group col-md-2">
		<select class="form-select bg-secondary text-light" id="month">
			<option value="">매매월선택</option>
		</select>
	</div>
</div>
<div class="row col-md-12 justify-content-center mb-2 mt-5 mb-3"
	style="margin-left: 205px">
	<c:choose>
		<c:when test="${not empty userInfo}">
			<div class="form-group col-md-2">
				<button id="aptLikeButton" type="button" class="btn btn-warning"
					style="width: 190px;">관심지역등록</button>
			</div>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${empty userInfo}">
			<div class="form-group col-md-2">
				<button id="aptLikeButton" type="button" class="btn btn-primary"
					style="width: 190px; visibility : hidden;">관심지역등록</button>
			</div>
		</c:when>
	</c:choose>
	<div class="form-group col-md-2">
		<button id="apt_deal" type="button" class="btn btn-primary" onclick=""
			style="width: 190px;">아파트 실거래가 조회</button>
	</div>
	<div class="form-group col-md-2">
		<button id="apt_detail" type="button" class="btn btn-primary"
			onclick="" style="width: 190px;">아파트 상세 조회</button>
	</div>
</div>
