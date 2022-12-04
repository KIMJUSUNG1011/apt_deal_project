<template>
  <!-- 상단 헤더/네브 바-->
  <!-- ======= Header/Navbar ======= -->
  <nav class="navbar navbar-default navbar-trans navbar-expand-lg fixed-top">
    <div class="container">
      <button
        class="navbar-toggler collapsed"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarDefault"
        aria-controls="navbarDefault"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span></span>
        <span></span>
        <span></span>
      </button>

      <router-link class="navbar-brand text-brand" v-bind:to="{ name: 'home' }">
        WhereIs<span class="color-b">MyHome</span>
      </router-link>

      <div class="navbar-collapse collapse justify-content-center" id="navbarDefault">
        <ul class="navbar-nav">
          <li class="nav-item">
            <router-link class="nav-link" v-bind:to="{ name: 'home' }">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" v-bind:to="{ name: 'propertyList' }"
              >Property</router-link
            >
          </li>
          <li class="nav-item">
            <router-link class="nav-link" v-bind:to="{ name: 'noticeList' }">Notice</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" v-bind:to="{ name: 'contact' }">Contact us</router-link>
          </li>
        </ul>
      </div>
      <div v-if="user.id != undefined" class="navbar-collapse collapse" style="width: 50px">
        <b>{{ user.name }}</b
        >&nbsp;님 환영합니다!!
      </div>

      <div>
        <button
          style="width: 37px; height: 37px"
          type="button"
          class="btn btn-b-n navbar-toggle-box navbar-toggle-box-collapse"
          data-bs-toggle="collapse"
          data-bs-target="#navbarTogglerDemo01"
          @click="setSido()"
        >
          <i class="bi bi-search"></i>
        </button>
      </div>

      <ul class="navbar-nav">
        <li class="nav-item dropdown" style="margin: 13px 0 0 50px">
          <a
            class="nav-link dropdown-toggle"
            href="#"
            id="navbarDropdown"
            role="button"
            data-bs-toggle="dropdown"
            aria-haspopup="true"
            aria-expanded="false"
          >
            <font-awesome-icon
              class="fa-2x"
              icon="fa-regular fa-user"
              style="width: 32px; height: 32px"
            />
          </a>
          <div class="dropdown-menu" style="width: 10px">
            <router-link
              v-if="$store.state.userStore.user.id == null"
              class="dropdown-item"
              v-bind:to="{ name: 'userLogin' }"
              >Sign in</router-link
            >
            <router-link
              v-if="$store.state.userStore.user.id == null"
              class="dropdown-item"
              v-bind:to="{ name: 'userRegist' }"
              >Sign up</router-link
            >
            <router-link
              v-if="$store.state.userStore.user.id != null"
              class="dropdown-item"
              v-bind:to="{ name: 'userDetail' }"
              >My Page</router-link
            >
            <a
              style="cursor: pointer"
              v-if="$store.state.userStore.user.id != null"
              class="dropdown-item"
              @click="logout({})"
              >Sign out</a
            >
          </div>
        </li>
      </ul>
    </div>
  </nav>
  <!-- End Header/Navbar -->
</template>

<script>
export default {
  data() {
    return {
      user: {},
    };
  },
  async created() {
    this.user = this.$store.state.userStore.user;
  },
  methods: {
    async logout() {
      await this.$store.dispatch("userStore/logout");
    },
    async setSido() {
      await this.$store.dispatch("aptStore/setSido");
      await this.$store.dispatch("aptStore/setGugun");
      this.$store.dispatch("aptStore/setDong");
    },
  },
};
</script>

<style></style>
