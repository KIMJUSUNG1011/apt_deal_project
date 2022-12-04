<template>
  <main id="main">
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">Our Amazing Properties</h1>
              <span class="color-text-a">지역별로 매물을 확인하세요</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">Property</li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->

    <!-- ======= Property Grid ======= -->
    <section class="property-grid grid">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="grid-option">
              <form>
                <select class="custom-select">
                  <option selected>All</option>
                  <option value="1">New to Old</option>
                </select>
              </form>
            </div>
          </div>
          <div class="col-md-4" v-for="(apt, index) in this.apts" v-bind:key="index">
            <div class="card-box-a card-shadow">
              <div class="img-box-a">
                <img
                  :src="apt.img"
                  alt=""
                  class="img-a img-fluid"
                  style="width: 500px; height: 400px"
                />
              </div>
              <div class="card-overlay">
                <div class="card-overlay-a-content">
                  <div class="card-header-a">
                    <div style="float: right">
                      <font-awesome-icon
                        v-if="
                          !checkLike(apt.apartmentName) &&
                          $store.state.userStore.user.id != undefined
                        "
                        class="fa-2x"
                        icon="fa-regular fa-heart"
                        style="width: 32px; height: 32px; color: rgb(53, 204, 111)"
                        @click="addLike(apt)"
                      />
                      <font-awesome-icon
                        v-if="
                          checkLike(apt.apartmentName) &&
                          $store.state.userStore.user.id != undefined
                        "
                        class="fa-2x"
                        icon="fa-solid fa-heart"
                        style="width: 32px; height: 32px; color: rgb(53, 204, 111)"
                        @click="deleteLike(apt.apartmentName)"
                      />
                      <p class="arrow_box">
                        하트를 3개 이상 등록하면 홈 화면에 좋아요 리스트가 나타납니다!!
                      </p>
                    </div>
                    <h2 class="card-title-a">
                      <a href="#">{{ apt.apartmentName }} <br /> </a>
                    </h2>
                  </div>
                  <div class="card-body-a">
                    <div class="price-box d-flex">
                      <span class="price-a">&#8361; {{ apt.dealAmount }} 만원</span>
                    </div>
                    <a @click="goPropertyDetail(apt)" href="#" class="link-a"
                      >Click here to view
                      <span class="bi bi-chevron-right"></span>
                    </a>
                  </div>
                  <div class="card-footer-a">
                    <ul class="card-info d-flex justify-content-around">
                      <li>
                        <h4 class="card-info-title">road name</h4>
                        <span>{{ apt.roadName }}</span>
                      </li>
                      <li>
                        <h4 class="card-info-title">build year</h4>
                        <span>{{ apt.buildYear }} 년</span>
                      </li>
                      <li>
                        <h4 class="card-info-title">area</h4>
                        <span>{{ apt.area }} m<sup>2</sup></span>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="clearfix">
              <nav class="pagination-a">
                <ul class="pagination">
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
    </section>
    <!-- End Property Grid Single-->
  </main>
  <!-- End #main -->
</template>

<script>
import axios from "axios";
import * as cheerio from "cheerio";
export default {
  data() {
    return {
      paging: {},
      apts: [],
      selectedApt: {},
      isSearchReq: 0,
      image: "",
    };
  },
  // 화면을 갱신하는 경우와 조건 검색으로 넘어왔을 때를 구분해야함
  // 일단 쿼리 스트링으로는 구별할 수 없음
  // state 의 sido, gugun, dong 값의 변화를 감지하여 처리
  async created() {
    let tmpPaging = await this.$store.state.aptStore.paging;
    let searchInfo = await this.$store.state.aptStore.searchInfo;
    let initPagingData = { nowPage: "1", cntPerPage: "6" };

    // 첫 요청이거나 검색에 의한 요청인 경우
    if (
      tmpPaging === undefined ||
      tmpPaging.nowPage == null ||
      this.$store.state.aptStore.isSearchReq == 1
    ) {
      if (searchInfo.keyword != undefined && searchInfo.keyword != "") {
        await this.$store.dispatch("aptStore/setAptList2", {
          paging: initPagingData,
          searchInfo: searchInfo,
        });
      } else {
        await this.$store.dispatch("aptStore/setAptList", {
          paging: initPagingData,
          searchInfo: searchInfo,
        });
      }
      this.$store.state.aptStore.isSearchReq = 0;
    } else {
      if (searchInfo.keyword != undefined && searchInfo.keyword != "") {
        await this.$store.dispatch("aptStore/setAptList2", {
          paging: tmpPaging,
          searchInfo: searchInfo,
        });
      } else {
        await this.$store.dispatch("aptStore/setAptList", {
          paging: tmpPaging,
          searchInfo: searchInfo,
        });
      }
    }
    this.apts = this.$store.state.aptStore.apts;
    this.paging = this.$store.state.aptStore.paging;
    console.log("apts : ", this.apts);
  },
  methods: {
    checkLike(aptName) {
      let aptList = this.$store.state.userStore.likeApts;
      let check = false;
      for (let i = 0; i < aptList.length; i++) {
        if (aptList[i].apartmentName == aptName) {
          check = true;
        }
      }
      return check;
    },
    addLike(apt) {
      console.log(apt);
      this.$store.dispatch("userStore/addLikeApt", apt);
    },
    deleteLike(aptName) {
      console.log(aptName);
      console.log(this.$store.state.userStore.user.id);
      let aptList = this.$store.state.userStore.likeApts;
      for (let i = 0; i < aptList.length; i++) {
        if (aptList[i].apartmentName == aptName) {
          this.$store.dispatch("userStore/deleteLikeApt", aptList[i].apartmentName);
        }
      }
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
      let searchInfo = await this.$store.state.aptStore.searchInfo;
      this.paging.nowPage = nowPage;
      if (nowPage == "&lt;") {
        this.paging.nowPage = this.paging.startPage - 1;
      }
      if (nowPage == "&gt;") {
        this.paging.nowPage = this.paging.endPage + 1;
      }
      await this.$store.dispatch("aptStore/setAptList", {
        paging: this.paging,
        searchInfo: searchInfo,
      });
      this.apts = this.$store.state.aptStore.apts;
      this.paging = this.$store.state.aptStore.paging;
      console.log("apts : ", this.apts);
      // // 스크롤 위로 복구
      window.scrollTo(0, 200);
    },
    async goPropertyDetail(apt) {
      await this.$store.dispatch("aptStore/setApt", apt);
      this.$router.push({ name: "propertyDetail" }).catch((failure) => {
        this.$router.push({ name: "propertyDetail" });
      });
    },
    async crawling() {
      let names = crawling_data();
      console.log(names.length + "개의 아파트 사진 정보 크롤링 시작");
      for (let name of names) {
        let url = "https://apis.zigbang.com/search?q=" + name;
        let img = "";
        let result = await axios.get(url);
        if (result.data.items[0] != null) {
          let id = result.data.items[0].id;
          let newUrl = "https://www.zigbang.com/home/apt/danjis/" + id;
          let resp = await axios.get(newUrl);
          const $ = cheerio.load(resp.data);
          const element = $("head");
          img = element.children()[23].attribs.content;
          await axios.post("http://192.168.130.62:80/home/apt/crawling", {
            apartmentName: name,
            img: img,
          });
          console.log(name + " clear!!");
        }
      }
    },
  },
};
</script>

<style scoped>
/* 말풍선 적절한 top 과 margin-left 로 위치조정 */
.arrow_box {
  opacity: 0;
  position: absolute;
  width: 210px;
  height: 80px;
  padding: 8px;
  right: 2%;
  -webkit-border-radius: 8px;
  -moz-border-radius: 8px;
  border-radius: 8px;
  background: rgb(158, 197, 81);
  color: black;
  font-size: 15px;
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  font-weight: bold;
}

.arrow_box:after {
  position: absolute;
  bottom: 100%;
  left: 50%;
  width: 0;
  height: 0;
  margin-left: -10px;
  border: solid transparent;
  border-color: rgba(51, 51, 51, 0);
  border-bottom-color: yellowgreen;
  border-width: 10px;
  pointer-events: none;
  content: " ";
}

.fa-2x:hover + p.arrow_box {
  opacity: 1;
  transition: all 1s;
}
.page-link:hover {
  cursor: pointer;
}

.pagination {
  float: right;
  margin: 0 0 5px;
}

.pagination li a {
  border: none;
  font-size: 13px;
  min-width: 30px;
  min-height: 30px;
  color: #999;
  margin: 0 2px;
  line-height: 30px;
  border-radius: 2px !important;
  text-align: center;
  padding: 0 6px;
}

.pagination li a:hover {
  color: #666;
}

.pagination li.active a,
.pagination li.active a.page-link {
  background: #03a9f4;
}

.pagination li.active a:hover {
  background: #0397d6;
}

.pagination li.disabled i {
  color: #ccc;
}

.pagination li i {
  font-size: 16px;
  padding-top: 6px;
}

.hint-text {
  float: left;
  margin-top: 10px;
  font-size: 13px;
}
</style>
