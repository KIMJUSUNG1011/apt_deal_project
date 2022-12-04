<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
function login(){
	let userId = document.getElementById('loginId').value;
    let pass = document.getElementById('loginPwd').value;
    console.log("userID : "+userId);
    console.log("pass : "+pass);
    const url = "/home/user/login?userId="+userId+"&pass="+pass;
    fetch(url,{
        method:'get',
    })
    .then(response=>{
		if(response.status==200){
			alert("로그인 성공!!");
		}else{
			alert("로그인 실패..");
		}
		location.href="/home";
	});
}

</script>
</head>
<body>
	<div class="modal fade" id="exampleModal_login" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">로그인</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">아이디</label> <input
								type="text" class="form-control" id="loginId" name="userId"/>
						</div>
						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">비밀번호</label> <input
								type="text" class="form-control" id="loginPwd" name="pass" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn" data-bs-toggle="modal"
								data-bs-target="#exampleModal_findPWD"
								data-bs-whatever="@getbootstrap">비밀번호 찾기</button>
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">취소</button>
							<button type="button" class="btn btn-primary"
								data-bs-dismiss="modal" onclick="login()">로그인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>

</html>