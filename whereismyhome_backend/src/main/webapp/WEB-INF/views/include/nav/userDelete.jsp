<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="exampleModal_withdraw" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">회원탈퇴</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">아이디</label> <input
								type="text" class="form-control" id="deleteid"
								name="userId" />
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">비밀번호</label> <input
								type="password" class="form-control" id="deletepass"
								name="pass" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-primary"
								data-bs-dismiss="modal"
								onclick="deleteUser()">회원탈퇴</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function deleteUser(){
	let	pass = document.getElementById('deletepass').value;
	let	userId = document.getElementById('deleteid').value;
	console.log("userId:"+userId);
	const user = {userId:userId,pass:pass};
	const url = "/home/user";
	
	fetch(url,{
		method:'delete',
		headers:{
			'Content-Type':'application/json',
		},
		body:JSON.stringify(user),
	})
	.then(response=>{
		if(response.status==200){
			location.href="/home";
			alert("회원탈퇴 성공!!");
		}else{
			alert("회원탈퇴 실패..");
		}
	});
}
</script>
</html>