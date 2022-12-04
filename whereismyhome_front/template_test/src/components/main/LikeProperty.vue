<template>
  <!-- 최신 매물 미리보기 -->
  <!-- ======= Latest Properties Section ======= -->
  <section class="section-property section-t8">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="title-wrap d-flex justify-content-between">
            <div class="title-box">
              <h2 class="title-a">Like Properties</h2>
            </div>
            <div class="title-link">
              <!-- <router-link
                v-if="this.aptLength >= 3 && $store.state.userStore.user.id != undefined"
                class="title-link"
                v-bind:to="{ name: 'propertyList' }"
                >Go Like Properties<span class="bi bi-chevron-right"></span
              ></router-link> -->
              <a
                v-if="this.aptLength >= 3 && $store.state.userStore.user.id != undefined"
                style="cursor: pointer"
                @click="message()"
                class="title-link"
                >Go Like Properties<span class="bi bi-chevron-right"></span
              ></a>
              <router-link
                v-if="
                  $store.state.userStore.user.id == undefined ||
                  (this.aptLength < 3 && $store.state.userStore.user.id != undefined)
                "
                class="title-link"
                v-bind:to="{ name: 'propertyList' }"
                >Go All Properties<span class="bi bi-chevron-right"></span
              ></router-link>
            </div>
          </div>
        </div>
      </div>
      <div v-if="$store.state.userStore.user.id == undefined">
        좋아요 기능은 로그인 후 사용 가능합니다!!
      </div>
      <div v-if="this.aptLength < 3 && $store.state.userStore.user.id != undefined">
        더 많은 좋아요가 필요합니다!!
      </div>
      <div
        id="property-carousel"
        class="swiper"
        v-if="this.aptLength >= 3 && $store.state.userStore.user.id != undefined"
      >
        <div class="swiper-wrapper">
          <div
            class="carousel-item-b swiper-slide"
            v-for="(apt, index) in this.apts"
            v-bind:key="index"
          >
            <div class="card-box-a card-shadow">
              <div class="img-box-a">
                <img
                  :src="apt.img"
                  alt=""
                  class="img-a img-fluid"
                  style="width: 417px; height: 460px"
                />
              </div>
              <div class="card-overlay">
                <div class="card-overlay-a-content">
                  <div class="card-header-a">
                    <h2 class="card-title-a">
                      <a href="#">{{ apt.apartmentName }}</a>
                    </h2>
                  </div>
                  <div class="card-body-a">
                    <div class="price-box d-flex">
                      <span class="price-a">&#8361; {{ apt.dealAmount }}</span>
                    </div>
                    <a @click="goPropertyDetail(apt)" class="link-a" href="#"
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
                        <span>{{ apt.buildYear }}</span>
                      </li>
                      <li>
                        <h4 class="card-info-title">area</h4>
                        <span>{{ apt.area }}</span>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- End carousel item -->
        </div>
        <div class="propery-carousel-pagination carousel-pagination"></div>
      </div>
    </div>
  </section>
  <!-- End Latest Properties Section -->
</template>

<script>
export default {
  data() {
    return {
      apts: [],
      aptLength: 0,
    };
  },
  async created() {
    let like_apts = this.$store.state.userStore.likeApts;
    this.aptLength = like_apts.length;
    if (like_apts.length >= 3) {
      for (let i = 0; i < like_apts.length; i++) {
        this.apts.push(like_apts[i]);
      }
    }
  },
  methods: {
    async goPropertyDetail(apt) {
      await this.$store.dispatch("aptStore/setApt", apt);
      this.$router.push({ name: "propertyDetail" }).catch((failure) => {
        this.$router.push({ name: "propertyDetail" });
      });
    },
    message() {
      alert("해당 기능은 향후 구현 예정입니다!!");
    },
  },
};
</script>

<style></style>
