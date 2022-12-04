import user from "@/util/user";
import { HttpStatusCode } from "axios";
import jwtDecode from "jwt-decode";
import store from "..";
const userStore = {
  namespaced: true,
  state: {
    token: "",
    user: {},
    likeApts: [],
    isDup: null
  },
  data() {
    return {
      user: {
        userId: "",
        pass: "",
      },
    };
  },
  getters: {},
  mutations: {
    SET_USER_IMG(state, payload) {
      console.log("payload:{}", payload);
      state.user.img = payload.user.img;
    },
    SET_USER(state, payload) {
      state.user = payload.user;
    },
    SET_TOKEN(state, payload) {
      state.token = payload.token;
    },
    SET_LIKE_APTS(state) {
      state.likeApts = [];
    },
    ADD_LIKE_APTS(state, payload) {
      console.log(payload);
      state.likeApts.push(payload);
    },
    DELETE_LIKE_APTS(state, payload) {
      let list = state.likeApts;
      console.log("DELETE_LIKE_APTS!!");
      for (let i = 0; i < list.length; i++) {
        if (list[i].apartmentName == payload) {
          console.log(i);
          list.splice(i, 1);
        }
      }
    },
    ID_CHECK(state, payload) {
      state.isDup = payload;
      console.log("payload : ", payload);
    }
  },
  actions: {
    idCheck(context, info) {
      console.log("아이디 중복체크 시도함");
      return user.post("/idCheck", info).then((res) => {
        console.log("res : ", res.data);
        context.commit("ID_CHECK", res.data);
        // console.log("store dup : ", this.state.isDup);
        if (res.data == 0) {
          alert("사용 가능한 아이디 입니다.");
        } else {
          alert("중복된 아이디 입니다.");
        }
      }).catch((error) => {
        alert("회원가입 중 문제가 발생했습니다.");
      });
    },
    addLikeApt(context, info) {
      console.log("addLikeApt접근");
      context.commit("ADD_LIKE_APTS", info);
    },
    deleteLikeApt(context, info) {
      console.log("deleteLikeApt접근");
      context.commit("DELETE_LIKE_APTS", info);
    },
    loginUser(context, userInfo) {
      return user
        .get("/login?userId=" + userInfo.userId + "&pass=" + userInfo.pass)
        .then(({ data }) => {
          let decodedToken = jwtDecode(data.token);
          console.log("토큰:", data.token);
          console.log(decodedToken);
          context.commit("SET_TOKEN", { token: data.token });
          context.commit("SET_USER", {
            user: {
              idx: decodedToken.idx,
              id: decodedToken.id,
              name: decodedToken.name,
              phone: decodedToken.phone,
              regdate: decodedToken.regdate,
              img: decodedToken.img,
            },
          });
          context.commit("SET_LIKE_APTS");
          alert("로그인에 성공하였습니다.");
          location.href = "/";
        })
        .catch(() => {
          alert("로그인에 실패하였습니다.");
          location.href = "/userLogin";
        });
    },
    logout({ commit }) {
      commit("SET_USER", {
        user: {},
      });
      commit("SET_TOKEN", {
        token: {},
      });
      alert("로그아웃 되었습니다.");
      location.href = "/";
    },
    signUpUser(context, userInfo) {
      console.log(context);
      return user
        .post("/", userInfo)
        .then((response) => {
          alert("회원가입에 성공하였습니다.");
          location.href = "/";
        })
        .catch((error) => {
          alert("회원가입에 실패하였습니다.");
          location.href = "/userRegist";
        });
    },
    updateUser(context, userInfo) {
      return user
        .put("/", userInfo)
        .then((response) => {
          alert("수정 성공!! 다시 로그인 하세요");
          context.commit("SET_USER", {
            user: {},
          });
          context.commit("SET_TOKEN", {
            token: {},
          });
          location.href = "/";
        })
        .catch((error) => {
          alert("회원정보 수정에 실패하였습니다.");
          location.href = "/userDetail";
        });
    },
    deleteUser(context, userInfo) {
      return user
        .delete("/", userInfo)
        .then((response) => {
          alert("탈퇴에 성공하였습니다.");
          context.commit("SET_USER", {
            user: {},
          });
          context.commit("SET_TOKEN", {
            token: {},
          });
          location.href = "/";
        })
        .catch((error) => {
          alert("탈퇴 실패!!");
          location.href = "/userDetail";
        });
    },
    findPass(context, userInfo) {
      return user
        .get("/findPass?userId=" + userInfo.userId + "&name=" + userInfo.name)
        .then((response) => {
          console.log("pass:", response.data);
          let eData = {
            userId: userInfo.userId,
            pass: response.data,
          };
          console.log("eData:", eData);
          store.dispatch("userStore/sendPassEmail", eData);
        });
    },
    sendPassEmail(context, sendInfo) {
      let eData = {
        name: "admin",
        email: sendInfo.userId,
        subject: "happyhouse findpass",
        message: "비밀번호 : " + sendInfo.pass,
      };
      return user.post("/findPassEmail", eData).then((response) => {
        console.log(eData);
      });
    },
    setImg(context, userInfo) {
      return user
        .post("/setImg", userInfo)
        .then((response) => {
          context.commit("SET_USER_IMG", {
            user: response.data,
          });
          console.log("img:{}", response.data.img);
          location.href = "/userDetail";
        })
        .catch((error) => {
          alert("실패..");
          location.href = "/userDetail";
        });
    },
  },
};

export default userStore;
