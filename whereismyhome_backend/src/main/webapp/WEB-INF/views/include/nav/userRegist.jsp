<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="exampleModal_register" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-id" class="col-form-label">아이디</label> <input
								type="text" class="form-control" id="registid" name="userId" />
						</div>
						<div class="mb-3">
							<label for="recipient-pwd" class="col-form-label">비밀번호</label> <input
								type="password" class="form-control" id="registpwd"
								name="pass" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">이름</label> <input
								type="text" class="form-control" id="registname" name="name" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">전화번호</label> <input
								type="text" class="form-control" id="registphone"
								name="phone" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">사진 경로</label>
							<input type="text" class="form-control" id="registimg"
								name="img" placeholder="'도형'을 입력 후 가입해주세요"/>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
							<button type="button" class="btn btn-primary" id="btn"
							    onclick="RegistUser()" data-bs-dismiss="modal">회원가입</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</body>
<script>
	function RegistUser(){
		let	userId = document.getElementById('registid').value;
		let	pass = document.getElementById('registpwd').value;
		let	name = document.getElementById('registname').value;
		let	phone = document.getElementById('registphone').value;
		let	img = document.getElementById('registimg').value;
		console.log("userId:"+userId);
		const user = {userId:userId,name:name,pass:pass,phone:phone,img:img};
		const url = "/home/user";
		
		fetch(url,{
			method:'post',
			headers:{
				'Content-Type':'application/json',
			},
			body:JSON.stringify(user),
		})
		.then(response=>{
			if(response.status==200){
				alert("회원가입 성공!!");
			}else{
				alert("회원가입 실패..");
			}
			location.href="/home";
		});
	}
</script>
</html>