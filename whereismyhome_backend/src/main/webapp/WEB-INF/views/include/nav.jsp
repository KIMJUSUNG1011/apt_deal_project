<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 상단 navbar start -->
<nav
	class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">
		<a class="navbar-brand text-primary fw-bold" href="${root}">
			<img src="${root}/assets/img/house.png" alt="" width="60" /> 구해줘 홈즈
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<!-- 로그인 전 -->
			<ul class="navbar-nav mb-2 me-2 mb-lg-0">
				<c:choose>
					<c:when test="${empty userInfo}">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="" data-bs-toggle="modal"
							data-bs-target="#exampleModal_register"
							data-bs-whatever="@getbootstrap">회원가입</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="#" data-bs-toggle="modal"
							data-bs-target="#exampleModal_login"
							data-bs-whatever="@getbootstrap">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${root}/user/mypage">마이페이지</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="${root}/user/logout" onclick="alert('로그 아웃 되었습니다.')">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/home/like/goLikeList">관심지역조회</a></li>
					</c:otherwise>
				</c:choose>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"
					aria-expanded="false"> 공지사항 </a>
					<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<c:choose>
							<c:when test="${not empty userInfo}">
								<li><a class="dropdown-item" href="#"
									data-bs-toggle="modal"
									data-bs-target="#exampleModal_noticeRegister"
									data-bs-whatever="@getbootstrap">공지사항 등록</a></li>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						<li><a class="dropdown-item"
							href="${root}/notice/goList">공지사항 조회</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</nav>

<!-- 상단 메뉴바에서 연결되는 페이지들 -->
<%@ include file="nav/userRegist.jsp"%>
<%@ include file="nav/findPwd.jsp"%>
<%@ include file="nav/login.jsp"%>
<%@ include file="nav/noticeRegist.jsp"%>
<%@ include file="nav/areaRegist.jsp"%>

<!-- 상단 navbar end -->