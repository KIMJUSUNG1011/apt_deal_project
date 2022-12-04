<template>
  <div>
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">{{ apt.apartmentName }}</h1>
              <span class="color-text-a" style="font-size: 20px">{{ apt.roadName }}</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item">
                  <a href="property-grid.html">Properties</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">{{ apt.apartmentName }}</li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->
    <!-- ======= Property Single ======= -->
    <section class="property-single nav-arrow-b">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="row justify-content-between">
              <div class="col-lg-6">
                <div id="property-single-carousel" class="swiper">
                  <div class="swiper-wrapper">
                    <div class="carousel-item-b swiper-slide">
                      <img :src="apt.img" alt="" />
                    </div>
                  </div>
                </div>
                <div class="property-single-carousel-pagination carousel-pagination"></div>
              </div>
              <div class="col-lg-6">
                <div class="property-summary">
                  <div class="row">
                    <div class="col-sm-12">
                      <div class="title-box-d section-t4" style="padding: 0px 0px 25px 0px">
                        <h3 class="title-d">상세 정보</h3>
                      </div>
                    </div>
                  </div>
                  <div class="summary-list">
                    <ul class="list">
                      <li
                        class="d-flex justify-content-between"
                        style="font-size: 20px; margin-bottom: 24px"
                      >
                        <strong>Name :</strong>
                        <span>{{ apt.apartmentName }}</span>
                      </li>
                      <li
                        class="d-flex justify-content-between"
                        style="font-size: 20px; margin-bottom: 24px"
                      >
                        <strong>Location:</strong>
                        <span>{{ apt.roadName }}</span>
                      </li>
                      <li
                        class="d-flex justify-content-between"
                        style="font-size: 20px; margin-bottom: 24px"
                      >
                        <strong>Average Amount:</strong>
                        <span>{{ apt.dealAmount }} 만원</span>
                      </li>
                      <li
                        class="d-flex justify-content-between"
                        style="font-size: 20px; margin-bottom: 24px"
                      >
                        <strong>Area</strong>
                        <span>{{ apt.area }} m<sup>2</sup></span>
                      </li>
                      <li
                        class="d-flex justify-content-between"
                        style="font-size: 20px; margin-bottom: 24px"
                      >
                        <strong>Build Year</strong>
                        <span>{{ apt.buildYear }} 년</span>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row col-sm-12">
            <div class="col-lg-7">
              <ul class="nav nav-pills-a nav-pills mb-3 section-t3" id="pills-tab" role="tablist">
                <li class="nav-item">
                  <a
                    class="nav-link active"
                    id="pills-map-tab"
                    data-bs-toggle="pill"
                    href="#pills-map"
                    role="tab"
                    aria-controls="pills-map"
                    aria-selected="true"
                    >Map</a
                  >
                </li>
                <li class="nav-item">
                  <a
                    class="nav-link"
                    id="pills-view-tab"
                    data-bs-toggle="pill"
                    href="#pills-view"
                    role="tab"
                    aria-controls="pills-view"
                    aria-selected="false"
                    >Road View</a
                  >
                </li>
              </ul>
              <div class="tab-content" id="pills-tabContent">
                <div
                  class="tab-pane fade show active"
                  role="tabpanel"
                  aria-labelledby="pills-map-tab"
                  id="pills-map"
                >
                  <div class="map_wrap" style="height: 460px">
                    <div
                      id="map"
                      style="width: 100%; height: 100%; position: relative; overflow: hidden"
                    ></div>
                    <!-- 지도타입 컨트롤 div 입니다 -->
                    <div class="custom_typecontrol radius_border" style="width: 134px">
                      <span id="btnRoadmap" class="selected_btn" @click="setMapType('roadmap')"
                        >지도</span
                      >
                      <span id="btnSkyview" class="btn" @click="setMapType('skyview')"
                        >스카이뷰</span
                      >
                    </div>
                    <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
                    <div class="custom_zoomcontrol radius_border">
                      <span @click="zoomIn()">
                        <font-awesome-icon icon="fa-solid fa-plus" style="padding-top: 12px" />
                      </span>
                      <span @click="zoomOut()">
                        <font-awesome-icon icon="fa-solid fa-minus" style="padding-top: 12px" />
                      </span>
                    </div>
                    <ul id="category">
                      <li id="BK9" data-order="0">
                        <span class="category_bg bank"></span>
                        은행
                      </li>
                      <li id="MT1" data-order="1">
                        <span class="category_bg mart"></span>
                        마트
                      </li>
                      <li id="PM9" data-order="2">
                        <span class="category_bg pharmacy"></span>
                        약국
                      </li>
                      <li id="OL7" data-order="3">
                        <span class="category_bg oil"></span>
                        주유소
                      </li>
                      <li id="CE7" data-order="4">
                        <span class="category_bg cafe"></span>
                        카페
                      </li>
                      <li id="CS2" data-order="5">
                        <span class="category_bg store"></span>
                        편의점
                      </li>
                    </ul>
                  </div>
                </div>
                <div
                  style="width: 100%; height: 460px"
                  class="tab-pane fade"
                  id="pills-view"
                  role="tabpanel"
                  aria-labelledby="pills-view-tab"
                ></div>
              </div>
            </div>
            <div class="col-lg-5">
              <ul class="nav nav-pills-a nav-pills mb-3 section-t3" id="pills-tab" role="tablist">
                <li class="nav-item">
                  <a
                    class="nav-link active"
                    id="pills-map-tab"
                    data-bs-toggle="pill"
                    href="#pills-map"
                    role="tab"
                    aria-controls="pills-map"
                    aria-selected="true"
                    >Deal List</a
                  >
                </li>
              </ul>
              <div class="tab-content" id="pills-tabContent">
                <table class="table table-striped table-hover">
                  <thead>
                    <tr>
                      <th style="width: 25%">매매가</th>
                      <th style="width: 33%">매매날짜</th>
                      <th style="width: 25%">면적</th>
                      <th style="width: 19%">층수</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr
                      style="cursor: pointer"
                      v-for="(deal, index) in dealList"
                      v-bind:key="index"
                    >
                      <td>{{ deal.dealAmount }} 만원</td>
                      <td>{{ deal.date }}</td>
                      <td>{{ deal.area }} m<sup>2</sup></td>
                      <td>{{ deal.floor }} 층</td>
                    </tr>
                  </tbody>
                </table>
                <div class="clearfix">
                  <nav class="pagination-a">
                    <ul class="pagination" style="float: right; cursor: pointer">
                      <li
                        v-for="(num, index) in list()"
                        :class="{ 'page-item active': paging.nowPage == num }"
                        v-bind:key="index"
                      >
                        <div class="page-link" @click="setting" ref="getPageNum" v-html="num"></div>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Property Single-->
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      apt: {},
      dealList: [],
      paging: {},
      map: null,
      roadview: null,
    };
  },
  async created() {
    this.apt = this.$store.state.aptStore.apt;
    let initPagingData = { nowPage: "1", cntPerPage: "9" };
    await this.$store.dispatch("aptStore/setDealList", {
      paging: initPagingData,
      aptCode: this.apt.aptCode,
    });
    this.dealList = this.$store.state.aptStore.dealList;
    this.paging = this.$store.state.aptStore.dealPaging;
    this.setDate();
    console.log("dealList : ", this.dealList);
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initRoadView();
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=24ec585764b419ed5640fa436441b129&libraries=services";
      script.onload = () => {
        window.kakao.maps.load(this.initRoadView);
        window.kakao.maps.load(this.initMap);
      };
      document.head.appendChild(script);
    }
  },
  methods: {
    async initMap() {
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(this.apt.lat, this.apt.lng),
        level: 3,
      };
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(this.apt.lat, this.apt.lng);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({ position: markerPosition });

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      var map = this.map;
      marker.setMap(this.map);

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(this.map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      // contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
      addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 커스텀 오버레이 띄우기
      let searchInfo = this.$store.state.aptStore.searchInfo;
      let url = "https://apis.zigbang.com/search?q=" + this.apt.apartmentName;
      let result = await axios.get(url);
      let newUrl = "";
      if (result.data.items[0] != null) {
        let id = result.data.items[0].id;
        newUrl = "https://www.zigbang.com/home/apt/danjis/" + id;
      } else {
        newUrl = "https://land.naver.com/";
      }
      let closeImg = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png";

      let content =
        '<div class="wrap" style="position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: "Malgun Gothic", dotum, "돋움", sans-serif;line-height: 1.5;padding: 0;margin: 0;">' +
        '    <div class="info" style="width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;">' +
        '        <div class="title" style="border: 0;box-shadow: 0px 1px 2px #888;padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;">' +
        this.apt.apartmentName +
        '            <div id="js_close" class="close" title="닫기" style="position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url(\'' +
        closeImg +
        "')\"></div>" +
        "        </div>" +
        '        <div class="body" style="position: relative;overflow: hidden;">' +
        '            <div class="img" style="position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;">' +
        '                <img src="' +
        this.apt.img +
        '" width="73" height="70">' +
        "           </div>" +
        '            <div class="desc" style="position: relative;margin: 13px 0 0 90px;height: 75px;">' +
        '                <div class="ellipsis" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' +
        searchInfo.sido +
        " " +
        searchInfo.gugun +
        " " +
        searchInfo.dong +
        "</div>" +
        '                <div class="jibun ellipsis" style="font-size: 11px;color: #888;margin-top: -2px;">(도로명)' +
        this.apt.roadName +
        " (지번)" +
        this.apt.jibun +
        "</div>" +
        '                <div><a href="' +
        newUrl +
        '" target="_blank" class="link" style="color: #5085BB;">사이트 이동</a></div>' +
        "            </div>" +
        "        </div>" +
        "    </div>" +
        "</div>";

      // 마커 위에 커스텀오버레이를 표시합니다
      // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
      let overlay = new kakao.maps.CustomOverlay({
        content: content,
        position: marker.getPosition(),
      });

      overlay.setMap(map);

      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(
        parseFloat(this.apt.lat) + 0.0004190241838,
        parseFloat(this.apt.lng) - 0.0004444774229
      );

      // 지도 중심을 이동 시킵니다
      map.setCenter(moveLatLon);

      // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
      kakao.maps.event.addListener(marker, "click", function () {
        overlay.setMap(map);
      });

      let js_close = document.getElementById("js_close");
      js_close.addEventListener("click", function () {
        console.log("close 오버레이");
        overlay.setMap(null);
      });

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }

      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status, pagination) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute("data-order");

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function (marker, place) {
            kakao.maps.event.addListener(marker, "click", function () {
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, order) {
        var imageSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          imgOptions = {
            spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      let houseImg = "/assets/img/apt/house.jpg";

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        console.log(place);
        let content =
          '<div class="wrap" style="position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: "Malgun Gothic", dotum, "돋움", sans-serif;line-height: 1.5;padding: 0;margin: 0;">' +
          '    <div class="info" style="width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;">' +
          '        <div class="title" style="border: 0;box-shadow: 0px 1px 2px #888;padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;">' +
          place.place_name +
          '            <div id="js_close_2" class="close" title="닫기" style="position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url(\'' +
          closeImg +
          "')\"></div>" +
          "        </div>" +
          '        <div class="body" style="position: relative;overflow: hidden;">' +
          '            <div class="img" style="position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;">' +
          '                <img src="' +
          houseImg +
          '" width="73" height="70">' +
          "           </div>" +
          '            <div class="desc" style="position: relative;margin: 13px 0 0 90px;height: 75px;">' +
          '                <div class="ellipsis" style="overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">' +
          place.address_name +
          "</div>" +
          '                <div class="jibun ellipsis" style="font-size: 11px;color: #888;margin-top: -2px;">' +
          place.category_name +
          "</div>" +
          '                <div class="jibun ellipsis" style="font-size: 11px;color: #888;margin-top: -2px;">' +
          place.phone +
          "</div>" +
          '                <div><a href="' +
          place.place_url +
          '" target="_blank" class="link" style="color: #5085BB;">사이트 이동</a></div>' +
          "            </div>" +
          "        </div>" +
          "    </div>" +
          "</div>";
        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);

        let js_close_2 = document.getElementById("js_close_2");
        js_close_2.addEventListener("click", function () {
          console.log("js_close_2 클릭");
          placeOverlay.setMap(null);
        });
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById("category"),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === "on") {
          currCategory = "";
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById("category"),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = "";
        }

        if (el) {
          el.className = "on";
        }
      }
    },
    // 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
    setMapType(maptype) {
      var roadmapControl = document.getElementById("btnRoadmap");
      var skyviewControl = document.getElementById("btnSkyview");
      if (maptype === "roadmap") {
        this.map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
        roadmapControl.className = "selected_btn";
        skyviewControl.className = "btn";
      } else {
        this.map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);
        skyviewControl.className = "selected_btn";
        roadmapControl.className = "btn";
      }
    },
    // 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    // 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
    },
    initRoadView() {
      var roadviewContainer = document.getElementById("pills-view"); //로드뷰를 표시할 div
      var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      var position = new kakao.maps.LatLng(this.apt.lat, this.apt.lng);

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
      });
    },
    list() {
      let list = [];
      if (this.paging.startPage != 1) {
        list.push("&lt");
      }
      for (let i = this.paging.startPage; i <= this.paging.endPage; i++) {
        list.push(i);
      }
      if (this.paging.endPage != this.paging.lastPage) {
        list.push("&gt");
      }
      return list;
    },
    async setting(e) {
      let nowPage = e.target.innerHTML;
      this.paging.nowPage = nowPage;
      if (nowPage == "&lt;") {
        this.paging.nowPage = this.paging.startPage - 1;
      }
      if (nowPage == "&gt;") {
        this.paging.nowPage = this.paging.endPage + 1;
      }
      await this.$store.dispatch("aptStore/setDealList", {
        paging: this.paging,
        aptCode: this.apt.aptCode,
      });
      this.dealList = this.$store.state.aptStore.dealList;
      this.paging = this.$store.state.aptStore.dealPaging;
      this.setDate();
      console.log("dealList : ", this.dealList);
    },
    setDate() {
      for (let i = 0; i < this.dealList.length; i++) {
        let deal = this.dealList[i];
        deal["date"] = deal.dealYear + "년 " + deal.dealMonth + "월 " + deal.dealDay + "일";
      }
    },
  },
};
</script>

<style scoped>
.d-flex {
  margin-bottom: 50px;
  font-size: large;
}

.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}

#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}

#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}

#category li.on {
  background: #eee;
}

#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}

#category li:last-child {
  margin-right: 0;
  border-right: 0;
}

#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}

#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}

#category li .bank {
  background-position: -10px 0;
}

#category li .mart {
  background-position: -10px -36px;
}

#category li .pharmacy {
  background-position: -10px -72px;
}

#category li .oil {
  background-position: -10px -108px;
}

#category li .cafe {
  background-position: -10px -144px;
}

#category li .store {
  background-position: -10px -180px;
}

#category li.on .category_bg {
  background-position-x: -46px;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}

.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}

.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}

.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}

.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}

.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}

.placeinfo .tel {
  color: #0f7833;
}

.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}

.map_wrap {
  overflow: hidden;
}

.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}

.custom_typecontrol {
  position: absolute;
  top: 10px;
  right: 10px;
  overflow: hidden;
  width: 130px;
  height: 30px;
  margin: 0;
  padding: 0;
  z-index: 1;
  font-size: 12px;
  font-family: "Malgun Gothic", "맑은 고딕", sans-serif;
}

.custom_typecontrol span {
  display: block;
  width: 65px;
  height: 30px;
  float: left;
  text-align: center;
  line-height: 30px;
  cursor: pointer;
}

.custom_typecontrol .btn {
  background: #fff;
  background: linear-gradient(#fff, #e6e6e6);
}

.custom_typecontrol .btn:hover {
  background: #f5f5f5;
  background: linear-gradient(#f5f5f5, #e3e3e3);
}

.custom_typecontrol .btn:active {
  background: #e6e6e6;
  background: linear-gradient(#e6e6e6, #fff);
}

.custom_typecontrol .selected_btn {
  color: #fff;
  background: #425470;
  background: linear-gradient(#425470, #5b6d8a);
}

.custom_typecontrol .selected_btn:hover {
  color: #fff;
}

.custom_zoomcontrol {
  position: absolute;
  top: 50px;
  right: 10px;
  width: 36px;
  height: 80px;
  overflow: hidden;
  z-index: 1;
  background-color: #f5f5f5;
}

.custom_zoomcontrol span {
  display: block;
  width: 36px;
  height: 40px;
  text-align: center;
  cursor: pointer;
}

.custom_zoomcontrol span img {
  width: 15px;
  height: 15px;
  padding: 12px 0;
  border: none;
}

.custom_zoomcontrol span:first-child {
  border-bottom: 1px solid #bfbfbf;
}

/* 커스텀 오버레이 */
.wrap_js {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap_js * {
  padding: 0;
  margin: 0;
}
.wrap_js .info_js {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap_js .info_js:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info_js .title_js {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info_js .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info_js .close:hover {
  cursor: pointer;
}
.info_js .body_js {
  position: relative;
  overflow: hidden;
}
.info_js .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
