<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/header.jsp"%>
</head>
<script type="text/javascript">



fetch("/home/user/getUser")
.then(response => response.json())
.then(data => makeUser(data));

function makeUser(userInfo){
	let field = ``;
	
	field += `
		<legend style="margin-bottom: 20px">마이페이지</legend>
		<div class="mb-3">
			<label for="disabledTextInput" class="form-label">아이디</label> <input
				type="text" id="disabledTextInput" class="form-control"
				placeholder="${userInfo.userId}">
		</div>
		<div class="mb-3">
			<label for="disabledTextInput" class="form-label">이름</label> <input
				type="text" id="disabledTextInput" class="form-control"
				placeholder="${userInfo.name}">
		</div>
		<div class="mb-3">
			<label for="disabledTextInput" class="form-label">전화번호</label> <input
				type="text" id="disabledTextInput" class="form-control"
				placeholder="${userInfo.phone}">
		</div>
		<div class="mb-3">
			<label for="disabledTextInput" class="form-label">가입일자</label> <input
				type="text" id="disabledTextInput" class="form-control"
				placeholder="${userInfo.registerDate}">
		</div>
		<div class="mb-3">
			<label for="disabledTextInput" class="form-label">사진</label>
			<div>
				<img src="${userInfo.img}" height="300px">
			</div>
		</div>
		<a aria-current="page" data-bs-toggle="modal"
			data-bs-target="#exampleModal_updateUserInfo"
			data-bs-whatever="@getbootstrap" type="submit"
			class="btn btn-primary" style="margin-right: 5px">회원정보수정</a> 
		<a aria-current="page" data-bs-toggle="modal"
			data-bs-target="#exampleModal_withdraw"
			data-bs-whatever="@getbootstrap" type="submit"
			class="btn btn-primary">회원탈퇴</a>
	`
	document.querySelector("#userinfos").innerHTML = field;
}

</script>
<body>
	<%@ include file="../../include/nav.jsp"%>

	<div class="container mt-5">
		<div style="height: 70px"></div>
		<div class="container-fluid"
			style="width: 750px; height: 820px; margin: 90px 250px; padding-top: 10px; background-color: lightgrey; font-weight: bold;">
			<form>
				<fieldset id="userinfos">
					
				</fieldset>
			</form>
		</div>
	</div>

	<%@ include file="userUpdate.jsp"%>
	<%@ include file="userDelete.jsp"%>

	<%@ include file="../../include/footer.jsp"%>
</body>
</html>