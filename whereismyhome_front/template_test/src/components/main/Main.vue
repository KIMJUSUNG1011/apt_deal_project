<template>
  <div>
    <intro />
    <service />
    <like-property />
    <latest-news />
    <testimonials />
  </div>
</template>

<script>
import Intro from "@/components/main/Intro.vue";
import Service from "@/components/main/Service.vue";
import LikeProperty from "@/components/main/LikeProperty.vue";
import LatestNews from "@/components/main/LatestNews.vue";
import Testimonials from "@/components/main/Testimonials.vue";
export default {
  name: "MainVue",
  components: {
    Intro,
    Service,
    LikeProperty,
    LatestNews,
    Testimonials,
  },
  mounted() {
    setting();
  },
};
</script>

<style></style>
