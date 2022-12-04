<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="modal fade" id="exampleModal_areaRegister" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">관심지역등록</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<label class="input-group-text" for="inputGroupSelect01">시도선택</label>
						<select class="form-select" id="inputGroupSelect01">
							<option value="1">서울특별시</option>
							<option value="2">부산광역시</option>
							<option value="3">대전광역시</option>
						</select>
					</div>
					<div class="input-group mb-3">
						<label class="input-group-text" for="inputGroupSelect01">구군선택</label>
						<select class="form-select" id="inputGroupSelect02">
							<option value="1">강남구</option>
							<option value="2">강북구</option>
							<option value="3">강서구</option>
						</select>
					</div>
					<div class="input-group mb-3">
						<label class="input-group-text" for="inputGroupSelect01">동선택</label>
						<select class="form-select" id="inputGroupSelect03"
							aria-label="Example select with button addon">
							<option value="1">삼각산동</option>
							<option value="2">길음동</option>
							<option value="3">보문동</option>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">취소</button>
					<button type="button" class="btn btn-primary"
						data-bs-dismiss="modal" onclick="alert('등록이 완료되었습니다.');">등록</button>
				</div>
			</div>
		</div>
	</div>
</body>

</html>