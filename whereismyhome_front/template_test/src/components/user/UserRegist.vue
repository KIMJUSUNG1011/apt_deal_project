<template>
  <div class="signup-form" style="margin-top: 140px">
    <form>
      <h2>Sign Up</h2>
      <p>Please fill in this form to create an account!</p>
      <hr />
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <span class="fa fa-user" style="width: 30px; height: 25px"></span>
            </span>
          </div>
          <input
            type="text"
            class="form-control"
            name="username"
            placeholder="Username"
            required="required"
            v-model="userInfo.name"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <i class="fa fa-paper-plane" style="width: 30px; height: 25px"></i>
            </span>
          </div>
          <input
            type="email"
            class="form-control"
            name="email"
            placeholder="Email Address"
            required="required"
            v-model="userInfo.userId"
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
            type="password"
            class="form-control"
            name="password"
            placeholder="Password"
            required="required"
            v-model="userInfo.pass"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <i class="fa fa-lock" style="width: 30px; height: 25px"></i>
              <i class="fa fa-check"></i>
            </span>
          </div>
          <input
            type="password"
            class="form-control"
            name="confirm_password"
            placeholder="Confirm Password"
            required="required"
            v-model="confirmPass"
          />
        </div>
      </div>
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text" style="margin-right: 7px">
              <i class="fa fa-phone" style="width: 30px; height: 25px"></i>
              <i class="fa fa-check"></i>
            </span>
          </div>
          <input
            type="text"
            class="form-control"
            name="phone_number"
            placeholder="Phone Number"
            required="required"
            v-model="userInfo.phone"
          />
        </div>
      </div>
      <div v-if="this.isDup == 0" class="form-group">
        <button type="button" class="btn btn-primary btn-lg" @click="signUp({})">Sign Up</button>
      </div>
      <div v-if="this.isDup != 0" class="form-group">
        <button type="button" class="btn btn-secondary btn-lg" @click="idCheck({})">
          Id Check
        </button>
      </div>
    </form>
    <div class="text-center">
      Already have an account?
      <router-link v-bind:to="{ name: 'userLogin' }">Login here</router-link>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      confirmPass: "",
      userInfo: {
        userId: "",
        pass: "",
        name: "",
        phone: "",
        img: "",
      },
      isDup: 1,
    };
  },
  methods: {
    signUp() {
      if (
        this.userInfo.pass == this.confirmPass &&
        this.userInfo.userId != "" &&
        this.userInfo.pass != "" &&
        this.userInfo.name != "" &&
        this.userInfo.phone != ""
      ) {
        this.$store.dispatch("userStore/signUpUser", this.userInfo);
      } else if (this.userInfo.pass != this.confirmPass) {
        alert("비밀번호 일치를 확인해 주세요.");
      } else {
        alert("사용자 정보를 모두 입력해주세요.");
      }
    },
    async idCheck() {
      if (this.userInfo.userId != "") {
        await this.$store.dispatch("userStore/idCheck", this.userInfo);
        console.log("aa : ", this.isDup);
        this.isDup = this.$store.state.userStore.isDup;
        console.log("aa : ", this.isDup);
        console.log("bb : ", this.$store.state.userStore.isDup);
      } else {
        alert("아이디를 입력해주세요.");
      }
    },
  },
  computed: {},
  name: "sign-up",
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
