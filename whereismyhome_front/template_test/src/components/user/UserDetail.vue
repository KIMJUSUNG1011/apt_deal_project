<template>
  <main id="main">
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">My Page</h1>
              <span class="color-text-a">show yourself</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4"></div>
        </div>
      </div>
    </section>
    <!-- End Intro Single -->

    <!-- ======= Agent Single ======= -->
    <section class="agent-single">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="row">
              <div class="col-md-6">
                <div class="agent-avatar-box">
                  <figure>
                    <img id="userImg" v-bind:src="user.img" alt="" class="agent-avatar img-fluid" />
                    <figcaption>
                      <input id="imginput" type="file" accept="image/*" @change="fileChange" />
                    </figcaption>
                  </figure>
                </div>
              </div>
              <div class="col-md-5 section-md-t3">
                <div class="agent-info-box" style="margin-left: 30px">
                  <div class="agent-title">
                    <div class="title-box-d">
                      <h3 class="title-d">
                        {{ user.name }}
                      </h3>
                    </div>
                  </div>
                  <div class="agent-content mb-3">
                    <div class="info-agents color-a">
                      <p id="name-info" style="display: none">
                        <strong>Name :</strong>
                        <input
                          style="margin-top: 5px"
                          v-model="user.name"
                          id="update-name"
                          class="form-control"
                          name="name"
                          placeholder="name"
                          required="required"
                        />
                      </p>
                      <p id="aphone-info">
                        <strong>Phone : </strong>
                        <span id="phone-info" class="color-text-a">{{ user.phone }}</span>
                        <input
                          style="margin-top: 5px"
                          v-model="user.phone"
                          id="update-phone"
                          type="hidden"
                          class="form-control"
                          name="phone"
                          placeholder="phone"
                          required="required"
                        />
                      </p>
                      <p id="email-info">
                        <strong>Email : </strong>
                        <span class="color-text-a"> {{ user.userId }}</span>
                      </p>
                      <p id="regdate-info">
                        <strong>Sign-Up-Date : </strong>
                        <span class="color-text-a"> {{ user.regdate }}</span>
                      </p>
                      <p id="pass-info" style="display: none">
                        <strong>Password : </strong>
                        <input
                          style="margin-top: 5px"
                          v-model="user.pass"
                          id="update-pass"
                          class="form-control"
                          name="password"
                          placeholder="password"
                          required="required"
                        />
                      </p>
                      <p id="passc-info" style="display: none">
                        <strong>PasswordConfirm : </strong>
                        <input
                          style="margin-top: 5px"
                          v-model="confirmPass"
                          id="update-pass-confirm"
                          class="form-control"
                          name="password-confirm"
                          placeholder="password-confirm"
                          required="required"
                        />
                      </p>
                    </div>
                  </div>
                  <div class="socials-footer">
                    <ul class="list-inline">
                      <li class="list-inline-item">
                        <a href="#" class="link-one">
                          <i class="bi bi-facebook" aria-hidden="true"></i>
                        </a>
                      </li>
                      <li class="list-inline-item">
                        <a href="#" class="link-one">
                          <i class="bi bi-twitter" aria-hidden="true"></i>
                        </a>
                      </li>
                      <li class="list-inline-item">
                        <a href="#" class="link-one">
                          <i class="bi bi-instagram" aria-hidden="true"></i>
                        </a>
                      </li>
                      <li class="list-inline-item">
                        <a href="#" class="link-one">
                          <i class="bi bi-linkedin" aria-hidden="true"></i>
                        </a>
                      </li>
                    </ul>
                  </div>
                  <div class="form-group">
                    <button
                      id="edit-button"
                      type="button"
                      class="btn btn-success"
                      style="margin-right: 7px"
                      @click="goUpdate({})"
                    >
                      Edit
                    </button>
                    <button
                      id="go-withdraw-button"
                      type="button"
                      class="btn btn-secondary"
                      @click="goWithdraw({})"
                    >
                      Withdrawal
                    </button>
                    <button
                      style="display: none"
                      id="withdraw-button"
                      type="button"
                      class="btn btn-success"
                      @click="withdrawUser({})"
                    >
                      Withdrawal
                    </button>
                    <button
                      style="display: none"
                      id="submit-button"
                      type="button"
                      value="confirm"
                      class="btn btn-success"
                      @click="userUpdate({})"
                    >
                      Submit
                    </button>
                    <button
                      style="display: none; margin-left: 7px"
                      id="cancel-button"
                      type="button"
                      class="btn btn-danger"
                      @click="cancelUpdate({})"
                    >
                      Cancel
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Agent Single -->
  </main>
  <!-- End #main -->
</template>

<script>
import { root } from "cheerio/lib/static";
export default {
  data() {
    return {
      file: "",
      confirmPass: "",
      user: {
        userId: "",
        name: "",
        phone: "",
        pass: "",
        regdate: "",
        img: "",
      },
    };
  },
  created() {
    this.user.userId = this.$store.state.userStore.user.id;
    this.user.name = this.$store.state.userStore.user.name;
    this.user.phone = this.$store.state.userStore.user.phone;
    this.user.regdate = this.$store.state.userStore.user.regdate;
    if (this.$store.state.userStore.user.img == "") {
      this.user.img = "assets/img/user-default.png";
    } else {
      this.user.img = this.$store.state.userStore.user.img;
    }
  },
  methods: {
    goUpdate() {
      document.getElementById("email-info").style.display = "none";
      document.getElementById("regdate-info").style.display = "none";
      document.getElementById("phone-info").style.display = "none";
      document.getElementById("edit-button").style.display = "none";
      document.getElementById("go-withdraw-button").style.display = "none";
      document.getElementById("cancel-button").style.display = "";
      document.getElementById("submit-button").style.display = "";
      document.getElementById("name-info").style.display = "";
      document.getElementById("pass-info").style.display = "";
      document.getElementById("passc-info").style.display = "";
      document.getElementById("update-phone").setAttribute("type", "text");
    },
    cancelUpdate() {
      location.reload();
    },
    userUpdate() {
      if (this.user.pass == this.confirmPass) {
        this.$store.dispatch("userStore/updateUser", this.user);
      } else {
        alert("비밀번호를 확인해주세요");
      }
    },
    goWithdraw() {
      document.getElementById("email-info").style.display = "none";
      document.getElementById("regdate-info").style.display = "none";
      document.getElementById("aphone-info").style.display = "none";
      document.getElementById("edit-button").style.display = "none";
      document.getElementById("go-withdraw-button").style.display = "none";
      document.getElementById("withdraw-button").style.display = "";
      document.getElementById("pass-info").style.display = "";
      document.getElementById("cancel-button").style.display = "";
    },
    withdrawUser() {
      if (confirm("정말로 탈퇴하시겠습니까?") == true) {
        console.log("탈퇴~");
        console.log("userid:", this.user.userId + "/pass:", this.user.pass);
        this.$store.dispatch("userStore/deleteUser", {
          data: {
            userId: this.user.userId,
            pass: this.user.pass,
          },
        });
      } else {
        console.log("안해~");
      }
    },
    setUserImg() {
      const formdata = new FormData();
      formdata.append("file", this.file);
      formdata.append("userId", this.user.userId);
      console.log("setUI:", this.file.name);
      console.log("setUSER:", this.user.userId);
      this.$store.dispatch("userStore/setImg", formdata, {
        Headers: {
          "Content-Type": "multipart/form-data",
        },
      });
    },
    fileChange(e) {
      const files = e.target.files;
      let validation = true;
      let message = "";

      if (files.length > 1) {
        validation = false;
        message = `파일은 한개만 등록 가능합니다.`;
      }
      if (files[0].size > 1024 * 1024 * 2) {
        message = `${message}, 파일은 용량은 2MB 이하만 가능합니다.`;
        validation = false;
      }

      if (files[0].type.indexOf("image") < 0) {
        message = `${message}, 이미지 파일만 업로드 가능합니다.`;
        validation = false;
      }
      if (validation) {
        this.file = files[0];
        this.setUserImg();
      } else {
        this.file = "";
        alert(message);
      }
    },
  },
};
</script>

<style>
#imginput {
  margin-top: 45%;
  margin-left: 25%;
}
.agent-avatar-box figure {
  display: block;
  width: 100%;
  height: 100%;
  position: relative;
}
.agent-avatar-box figcaption {
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
  color: #fff;
  text-align: center;
  line-height: 50px;
  opacity: 0;
  transition: 0.3s;
}
.agent-avatar-box figcaption:hover,
.agent-avatar-box figcaption:hover {
  opacity: 1;
}
</style>
