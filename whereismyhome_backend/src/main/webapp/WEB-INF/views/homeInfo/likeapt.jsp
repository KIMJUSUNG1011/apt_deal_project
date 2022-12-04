<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../include/header.jsp"%>
</head>
<body>
	<%@ include file="../include/nav.jsp"%>

	<div class="container mt-5">
	
		<!-- 선택 및 조회 버튼 -->
		<%@ include file="../include/content2.jsp"%>

		<!-- 리스트 화면 및 지도 -->
		<div class="row" style="width: 1065px; margin-left: 110px">

			<div class="col-5"
				style="height: 500px; margin-top: 40px; border: 1px solid black; background-color: white; font-weight: bold; overflow:auto">
				<!-- 테이블 삽입 -->
				<table class="table">
					<thead id ="thead">
					</thead>
					<tbody id="tbody">
					</tbody>
				</table>
			</div>

			<div class="col-7">
				<div class="container-fluid" id="map"
					style="height: 500px; margin-top: 40px"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a48abf1e351b792608d5d7d693665759"></script>
				<script>
					function func(lng, lat) {
						
						var pos = new kakao.maps.LatLng(lat, lng);
						
						var marker = new kakao.maps.Marker({
							map : map,
							position : pos
						});
						
						marker.setMap(map);
						map.panTo(pos);
					}

					var container = document.getElementById('map');
					var options = {
						center : new kakao.maps.LatLng(33.450701, 126.570667),
						level : 3
					};
					var map = new kakao.maps.Map(container, options);
				</script>
			</div>
		</div>

	</div>

	<%@ include file="../include/footer.jsp"%>
</body>

<script>
	// 관심 지역 옵션 세팅
	setLikeSelect()
</script>
</html>