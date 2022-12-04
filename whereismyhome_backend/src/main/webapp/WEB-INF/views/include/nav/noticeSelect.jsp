<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../../include/header.jsp"%>
</head>
<body>
	<%@ include file="../../include/nav.jsp"%>

	<div class="container mt-5">
		<div style="height: 70px"></div>
		<div class="container-fluid"
			style="width: 750px; height: 680px; margin: 30px 250px; padding-top: 10px; background-color: lightgrey; font-weight: bold;">
			<form>
				<fieldset>
					<legend style="margin-bottom: 20px">공지사항 상세보기</legend>
					<div class="mb-3">
						<label for="disabledTextInput" class="form-label"></label>글 번호<input
							type="text" id="noticeIdx" class="form-control"
							placeholder="${notice.noticeIdx}">
					</div>
					<div class="mb-3">
						<label for="disabledTextInput" class="form-label"></label>작성자<input
							type="text" id="noticeName" class="form-control"
							placeholder="${notice.name}">
					</div>
					<div class="mb-3">
						<label for="disabledTextInput" class="form-label">제목</label> <input
							type="text" id="noticeTitle" class="form-control"
							placeholder="${notice.title}">
					</div>
					<div class="mb-3">
						<label for="disabledTextInput" class="form-label">내용</label>
						<textarea class="form-control" id="noticeContent" rows="7"
							placeholder="${notice.content}"></textarea>
					</div>
					<div class="mb-3">
						<label for="disabledTextInput" class="form-label">작성일자</label> <input
							type="text" id="noticeRegisterDate" class="form-control"
							placeholder="${notice.registerDate}">
					</div>
					<a aria-current="page" data-bs-toggle="modal"
						data-bs-target="#exampleModal_noticeUpdate"
						data-bs-whatever="@getbootstrap" type="submit"
						class="btn btn-primary" style="margin-right: 5px">공지사항 수정</a> <a
						href="javascript:remove()" class="btn btn-primary">공지사항 삭제</a>
				</fieldset>
			</form>
		</div>
	</div>

	<%@ include file="noticeUpdate.jsp"%>

	<%@ include file="../../include/footer.jsp"%>
</body>

<script src="${root}/assets/js/notice.js"></script>

<script>
	// 공지사항 상세보기
	view();
</script>

</html>