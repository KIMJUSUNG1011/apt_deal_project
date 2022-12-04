<template>
  <!-- 최신 공지사항 미리보기 -->
  <!-- ======= Latest News Section ======= -->
  <section class="section-news section-t8">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="title-wrap d-flex justify-content-between">
            <div class="title-box">
              <h2 class="title-a">Latest News</h2>
            </div>
            <div class="title-link">
              <a href="https://land.naver.com/news/headline.naver"
                >Go All News
                <span class="bi bi-chevron-right"></span>
              </a>
            </div>
          </div>
        </div>
      </div>

      <div id="news-carousel" class="swiper">
        <div class="swiper-wrapper">
          <div
            class="carousel-item-c swiper-slide"
            v-for="(news, index) in newsList"
            v-bind:key="index"
          >
            <div class="card-box-b card-shadow news-box">
              <div class="img-box-b">
                <img :src="news.img" alt="" class="img-b img-fluid" style="height: 418px" />
              </div>
              <div class="card-overlay">
                <div class="card-header-b">
                  <div class="card-title-b">
                    <h2 class="title-2">
                      <a :href="news.link">
                        <div v-html="news.title"></div>
                      </a>
                    </h2>
                  </div>
                  <div class="card-date">
                    <span class="date-b">{{ news.pubDate }}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- End carousel item -->
        </div>
        <div class="news-carousel-pagination carousel-pagination"></div>
      </div>
    </div>
  </section>
  <!-- End Latest News Section -->
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      getHTML: "",
      newsList: [],
    };
  },
  async created() {
    let config = {
      headers: {
        "X-Naver-Client-Id": "d8vXH_wofT9MKxDiyHsc",
        "X-Naver-Client-Secret": "lLXmjqKWu5",
      },
    };
    let url = "/v1/search/news.json?query=" + encodeURI("부동산") + "&display=3";
    this.getHTML = await axios.get(url, config);
    this.newsList = this.getHTML.data.items;
    for (let i = 1; i <= this.newsList.length; i++) {
      this.newsList[i - 1]["img"] = "assets/img/news/art" + i + ".jpg";
    }
  },
};
</script>

<style></style>
