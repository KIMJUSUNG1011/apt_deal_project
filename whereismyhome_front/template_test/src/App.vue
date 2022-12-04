<template>
  <div id="app">
      <property-search />
      <header-nav />
      <router-view :key="$route.fullPath"/>
      <!-- <router-view/> -->
      <footer-nav />
      <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
          class="bi bi-arrow-up-short"></i></a>
  </div>
</template>

<script>
import HeaderNav from "./components/common/HeaderNav.vue";
import FooterNav from "./components/common/FooterNav.vue";
import PropertySearch from "./components/common/PropertySearch.vue";
export default {
  name: "HomeVue",
  components: {
    HeaderNav,
    FooterNav,
    PropertySearch,
  },
  mounted() {
    setting();
  },
  // 이 곳에 compted, watch 를 둠으로서
  // 사이트 모든 곳에서 아파트 검색으로 아파트 리스트 화면으로 이동함을 감지할 수 있다.
  computed: {
    check_searchInfo_change() {
      return this.$store.state.aptStore.searchInfo.dong;
    }
  },
  watch: {
    check_searchInfo_change() {
      console.log("PropertyList 에서 SearchInfo 의 변화를 감지하였습니다.")
      this.$store.state.aptStore.isSearchReq = 1;
    }
  },
};
</script>

<style>
</style>
