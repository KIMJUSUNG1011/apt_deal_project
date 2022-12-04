<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../header.jsp"%>
</head>
<body>
	<%@ include file="../nav.jsp"%>

	<div class="container mt-5">
		<div style="height: 100px"></div>

		<table class="table">
			<thead class="table-light">
				<tr>
					<th scope="col">#</th>
					<th scope="col">작성자</th>
					<th scope="col">제목</th>
					<th scope="col">내용</th>
					<th scope="col">작성일자</th>
				</tr>
			</thead>
			<tbody id="noticeTbody">
 			</tbody>
		</table>
	</div>

	<%@ include file="../footer.jsp"%>
</body>

<script src="${root}/assets/js/notice.js"></script>

<script>
	// 공지사항 목록 보기
	list();
</script>

</html>