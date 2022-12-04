<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="exampleModal_updateUserInfo" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">회원 정보 수정</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-pwd" class="col-form-label">비밀번호</label> <input
								type="password" class="form-control" id="updatePwd"
								name="pass" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">이름</label> <input
								type="text" class="form-control" id="updateName" name="name" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">전화번호</label> <input
								type="text" class="form-control" id="updatePhone"
								name="phone" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">사진 경로</label>
							<input type="text" class="form-control" id="updateImg"
								name="img" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
							<button type="button" class="btn btn-primary"
								onclick="updateUser()" data-bs-dismiss="modal">
								회원 정보 변경</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function updateUser(){
		let	pass = document.getElementById('updatePwd').value;
		let	name = document.getElementById('updateName').value;
		let	phone = document.getElementById('updatePhone').value;
		let	img = document.getElementById('updateImg').value;
		console.log("name:"+name);
		const user = {name:name,pass:pass,phone:phone,img:img};
		const url = "/home/user";
		
		fetch(url,{
			method:'put',
			headers:{
				'Content-Type':'application/json',
			},
			body:JSON.stringify(user),
		})
		.then(response=>{
			if(response.status==200){
				alert("회원수정 성공!!");
			}else{
				alert("회원수정 실패..");
			}
			location.href="/home";
		});
	}
</script>
</html>