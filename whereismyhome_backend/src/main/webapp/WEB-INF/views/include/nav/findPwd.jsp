<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.modal-backdrop {
    visibility: hidden !important;
}

.modal.in {
    background-color: rgba(0, 0, 0, 0.5);
}
</style>
<script type="text/javascript">
function findPwd(){
    let userId = document.getElementById('id').value;
    let name = document.getElementById('name').value;
    console.log("userID : "+userId);
    console.log("name : "+name);
    const user = {userId:userId,name:name};
    const url = "/home/user/findPass/"+user;
    fetch(url,{
        method:'get',
    })
    .then(response=>response.json())
    .then(data=>alert(data));
} 
function findPwd2(){
    let userId = document.getElementById('id').value;
    let name = document.getElementById('name').value;
    console.log("userID : "+userId);
    console.log("name : "+name);
    const url = "/home/user/findPass?userId="+userId+"&name="+name;
    fetch(url,{
        method:'get',
    })
    .then(response=>response.json())
    .then(data=>alert(data));
    
}

</script>
</head>
<body>
    <div class="modal fade" id="exampleModal_findPWD" tabindex="-1"
        aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">비밀번호 찾기</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="${root}/user/findPass" method="POST">
                        <div class="mb-3">
                            <label for="recipient-id" class="col-form-label">아이디</label> <input
                                type="text" class="form-control" name="userId"
                                id="id" />
                        </div>
                        <div class="mb-3">
                            <label for="recipient-name" class="col-form-label">이름</label> <input
                                type="text" class="form-control" id="name" name = "name" />
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal" data-toggle="modal">취소</button>
                    <button id="selbtn" type="button" class="btn btn-primary"
                        data-bs-dismiss="modal" data-toggle="modal"
                        onclick="findPwd2()">찾기</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>