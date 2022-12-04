<template>
  <div>
    <!-- 매물 검색 컴포넌트-->
    <!-- ======= Property Search Section ======= -->
    <div class="click-closed"></div>
    <!--/ Form Search Star /-->
    <div class="box-collapse">
      <div class="title-box-d">
        <h3 class="title-d">Search Apt Deal</h3>
      </div>
      <span class="close-box-collapse right-boxed bi bi-x"></span>
      <div class="box-collapse-wrap form">
        <form class="form-a">
          <div class="row">
            <div class="col-md-12 mb-2">
              <div class="form-group">
                <label class="pb-2" for="Type">Keyword</label>
                <input
                  v-model="searchInfo.keyword"
                  type="text"
                  class="form-control form-control-lg form-control-a"
                  placeholder="검색어는 반드시 시/도, 구/군, 동과 함께 입력해주세요."
                />
              </div>
            </div>
            <div class="col md-6 mb-2">
              <div class="form-group mt-3">
                <label class="pb-2" for="Type">Sido</label>
                <select
                  class="form-control form-select form-control-a"
                  id="sido"
                  v-on:change="setGugun()"
                ></select>
              </div>
            </div>
            <div class="col md-6 mb-2">
              <div class="form-group mt-3">
                <label class="pb-2" for="city">Gugun</label>
                <select
                  class="form-control form-select form-control-a"
                  id="gugun"
                  v-on:change="setDong()"
                ></select>
              </div>
            </div>
            <div class="col md-6 mb-2">
              <div class="form-group mt-3">
                <label class="pb-2" for="bedrooms">Dong</label>
                <select
                  class="form-control form-select form-control-a"
                  id="dong"
                  @change="setSearchInfo()"
                ></select>
              </div>
            </div>
            <!-- <div class="col-md-6 mb-2">
              <div class="form-group mt-3">
                <label class="pb-2" for="bathrooms">매매년도</label>
                <select
                  class="form-control form-select form-control-a"
                  id="year"
                  v-on:change="setMonth()"
                ></select>
              </div>
            </div>
            <div class="col-md-6 mb-2">
              <div class="form-group mt-3">
                <label class="pb-2" for="price">매매월</label>
                <select class="form-control form-select form-control-a" id="month"></select>
              </div>
            </div> -->
            <!-- <div class="col-md-12" style="margin-top: 20px">
                <button type="button" class="btn btn-b" @click="searchApt()">Search Property</button>
            </div> -->
            <div class="col-md-12" style="margin-top: 20px">
              <button type="button" class="btn btn-b" @click="searchApt()">Search Property</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- End Property Search Section -->
</template>

<script>
export default {
  data() {
    return {
      searchInfo: {
        sido: "",
        gugun: "",
        dong: "",
        keyword: "",
      },
      // 아파트 검색 시 화면을 업데이트하게 해주는 변수
      // App.vue 에 router-view 에 설정이 필요함
      // 아파트 검색으로 아파트 리스트에 들어가서 또 아파트 검색을 할 경우 uri 가 고정되어 있어
      // 화면이 갱신되지 않음(뷰 객체가 다시 만들어지지 않음)
      // 따라서 uri 를 매번 다르게 줌으로서 매번 객체가 새로 생성되어 데이터가 갱신되도록 함
      clickCnt: 0,
    };
  },
  methods: {
    async setGugun() {
      await this.$store.dispatch("aptStore/setGugun");
    },
    async setDong() {
      await this.$store.dispatch("aptStore/setDong");
    },
    // async setMonth() {
    //   await this.$store.dispatch("aptStore/setMonth");
    // },
    setSearchInfo() {
      let sidoSel = document.getElementById("sido");
      let gugunSel = document.getElementById("gugun");
      let dongSel = document.getElementById("dong");
      let sidoVal = sidoSel[sidoSel.selectedIndex].value;
      let gugunVal = gugunSel[gugunSel.selectedIndex].value;
      let dongVal = dongSel[dongSel.selectedIndex].value;
      this.searchInfo.sido = sidoVal;
      this.searchInfo.gugun = gugunVal;
      this.searchInfo.dong = dongVal;
    },
    async searchApt() {
      if (this.searchInfo.dong != "") {
        await this.$store.dispatch("aptStore/searchApt", this.searchInfo);

        // this.$router.push({ name: "propertyList"});
        this.$router.push({ name: "propertyList", query: { clickCnt: this.clickCnt } });
        this.clickCnt++;
        let body = document.querySelector("body");
        body.classList.remove("box-collapse-open");
        body.classList.add("box-collapse-closed");
      } else {
        alert("검색 정보를 입력해주세요");
      }
    },
  },
};
</script>

<style></style>
