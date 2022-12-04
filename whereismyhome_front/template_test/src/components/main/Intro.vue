<template>
  <!-- ======= Intro Section ======= -->
  <div class="intro intro-carousel swiper position-relative">
    <div class="swiper-wrapper">
      <div
        class="swiper-slide carousel-item-a intro-item bg-image"
        style="background-image: url(assets/img/apt/apt1.jpg)"
      >
        <div class="overlay overlay-a"></div>
        <div class="intro-content display-table">
          <div class="table-cell">
            <div class="container">
              <div class="row">
                <div class="col-lg-8">
                  <div class="intro-body">
                    <!-- <p class="intro-title-top">서울특별시<br />강북구 미아동</p> -->
                    <h1 class="intro-title mb-4">
                      <span class="color-b">솔샘로</span><br />
                      SK북한산시티
                    </h1>
                    <p class="intro-subtitle intro-price">
                      <a href="#"><span class="price-a">&#8361; 46695.31 만원</span></a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="swiper-slide carousel-item-a intro-item bg-image"
        style="background-image: url(assets/img/apt/apt2.jpg)"
      >
        <div class="overlay overlay-a"></div>
        <div class="intro-content display-table">
          <div class="table-cell">
            <div class="container">
              <div class="row">
                <div class="col-lg-8">
                  <div class="intro-body">
                    <!-- <p class="intro-title-top">
                      Doral, Florida <br />
                      78345
                    </p> -->
                    <h1 class="intro-title mb-4">
                      <span class="color-b">삼양로27길</span><br />
                      두산위브트레지움
                    </h1>
                    <p class="intro-subtitle intro-price">
                      <a href="#"><span class="price-a">&#8361; 55138.65 만원</span></a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div
        class="swiper-slide carousel-item-a intro-item bg-image"
        style="background-image: url(assets/img/apt/apt3.jpg)"
      >
        <div class="overlay overlay-a"></div>
        <div class="intro-content display-table">
          <div class="table-cell">
            <div class="container">
              <div class="row">
                <div class="col-lg-8">
                  <div class="intro-body">
                    <!-- <p class="intro-title-top">
                      Doral, Florida <br />
                      78345
                    </p> -->
                    <h1 class="intro-title mb-4">
                      <span class="color-b">삼양로19길</span><br />
                      삼각산아이원
                    </h1>
                    <p class="intro-subtitle intro-price">
                      <a href="#"><span class="price-a">&#8361; 50642.8 만원</span></a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="swiper-pagination"></div>
  </div>
  <!-- End Intro Section -->
</template>

<script>
export default {
  data() {
    return {
      apts: [],
      apt0: {},
      apt1: {},
      apt2: {},
    };
  },
  async created() {
    let searchInfo = await this.$store.state.aptStore.searchInfo;
    let initPagingData = { nowPage: "1", cntPerPage: "6" };
    // await this.$store.dispatch("aptStore/setAptList", {
    //   paging: initPagingData,
    //   searchInfo: searchInfo,
    // });
    this.apts = this.$store.state.aptStore.apts;
    this.apt0 = this.apts[0];
    this.apt1 = this.apts[1];
    this.apt2 = this.apts[2];
    console.log("apt0 : ", this.apt0.img);
  },
};
</script>

<style></style>
