<template>
  <div class="signup-form" style="margin-top: 140px">
    <form>
      <h2>Sign In</h2>
      <p>Please fill in this form to sign in!</p>
      <hr />
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <i class="fa fa-paper-plane" style="width: 30px; height: 25px"></i>
            </span>
          </div>
          <input
            v-model="userInfo.userId"
            type="email"
            class="form-control"
            name="email"
            placeholder="Email Address"
            required="required"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <i class="fa fa-lock" style="width: 30px; height: 25px"></i>
            </span>
          </div>
          <input
            v-model="userInfo.pass"
            type="password"
            class="form-control"
            name="password"
            placeholder="Password"
            required="required"
          />
        </div>
      </div>
      <div class="form-group">
        <label class="form-check-label"><input type="checkbox" /> Remember me</label>
      </div>
      <div class="form-group" style="text-align: center">
        <button
          style="background: #19aa8d"
          type="button"
          class="btn btn-primary btn-lg"
          @click="login({})"
        >
          Sign In
        </button>
        <button
          style="margin-left: 8%"
          type="button"
          class="btn btn-secondary btn-lg"
          @click="goFindPass()"
        >
          Find Pass
        </button>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userInfo: {
        userId: "",
        pass: "",
      },
    };
  },
  methods: {
    goFindPass() {
      console.log("go!");
      this.$router.push({ name: "userFindPass" });
    },

    async login() {
      if (this.userInfo.userId != "" && this.userInfo.pass != "") {
        await this.$store.dispatch("userStore/loginUser", this.userInfo);
        console.log("login:", this.$store.state.userStore.user);
      } else {
        alert("모든 내용을 입력해주세요.");
      }
    },
  },
  computed: {
    user() {
      return this.$store.state.user;
    },
  },
  name: "sign-in",
};
</script>

<style scoped>
@import "https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css";
body {
  color: #fff;
  font-family: "Roboto", sans-serif;
}
.form-control {
  font-size: 15px;
}
.form-control,
.form-control:focus,
.input-group-text {
  border-color: #e1e1e1;
}
.form-control,
.btn {
  border-radius: 3px;
}
.signup-form {
  width: 400px;
  margin: 0 auto;
  padding: 30px 0;
}
.signup-form form {
  color: #999;
  border-radius: 3px;
  margin-bottom: 15px;
  background: #fff;
  box-shadow: 2px 2px 2px 2px rgba(0.3, 0.3, 0.3, 0.3);
  padding: 30px;
}
.signup-form h2 {
  color: #333;
  font-weight: bold;
  margin-top: 0;
}
.signup-form hr {
  margin: 0 -30px 20px;
}
.signup-form .form-group {
  margin-bottom: 20px;
}
.signup-form label {
  font-weight: normal;
  font-size: 15px;
}
.signup-form .form-control {
  min-height: 38px;
  box-shadow: none !important;
}
.signup-form .input-group-addon {
  max-width: 42px;
  text-align: center;
}
.signup-form .btn,
.signup-form .btn:active {
  font-size: 16px;
  font-weight: bold;
  /* background: #19aa8d !important; */
  border: none;
  min-width: 140px;
}
.signup-form .btn:hover,
.signup-form .btn:focus {
  /* background: #179b81 !important; */
}
.signup-form a {
  color: blue;
  text-decoration: underline;
}
.signup-form a:hover {
  text-decoration: none;
}
.signup-form form a {
  color: #19aa8d;
  text-decoration: none;
}
.signup-form form a:hover {
  text-decoration: underline;
}
.signup-form .fa {
  font-size: 21px;
}
.signup-form .fa-paper-plane {
  font-size: 18px;
}
.signup-form .fa-check {
  color: #fff;
  left: 17px;
  top: 18px;
  font-size: 7px;
  position: absolute;
}
</style>
