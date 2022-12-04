<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="exampleModal_noticeUpdate" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">공지사항 수정</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="mb-3">
						<label for="recipient-name" class="col-form-label">제목</label> <input
							type="text" class="form-control" id="noticeUpdateTitle" name="title" />
					</div>
					<div class="mb-3">
						<label for="recipient-name" class="col-form-label">내용</label>
						<textarea class="form-control" id="noticeUpdateContent" name="content"
							style="height: 100px"></textarea>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							data-bs-dismiss="modal" onclick="update()">수정</button>
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script src="${root}/assets/js/notice.js"></script>

</html>