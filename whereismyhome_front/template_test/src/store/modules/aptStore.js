import apt from "@/util/apt";
import { HttpStatusCode } from "axios";
import { BIconEmojiSmileUpsideDown } from "bootstrap-vue";
const aptStore = {
  namespaced: true,
  state: {
    searchInfo: {
      sido: "서울특별시",
      gugun: "강북구",
      dong: "미아동",
    },
    apts: [],
    paging: {},
    dealList: [],
    dealPaging: {},
    apt: {},
    // 아파트 조건 검색에 의한 요청인지 아닌지를 구분하는 변수
    isSearchReq: 0,
  },
  getters: {},
  mutations: {
    SET_SEARCH_INFO(state, payload) {
      state.searchInfo = payload.searchInfo;
    },
    SET_APT_LIST(state, payload) {
      state.paging = payload.paging;
      state.apts = payload.viewAll;
    },
    SET_APT(state, payload) {
      state.apt = payload;
    },
    SET_DEAL_LIST(state, payload) {
      state.dealPaging = payload.paging;
      state.dealList = payload.viewAll;
    }
  },
  actions: {
    setYear(context) {
      let yearSel = document.querySelector("#year");
      let yearOpt = '<option value ="">default</option>';
      let date = new Date();
      let year = date.getFullYear();
      console.log(year);
      for (let i = year; i >= year - 20; i--) {
        yearOpt += "<option value='" + i + "'>" + i + "년</option>";
      }
      yearSel.innerHTML = yearOpt;
    },
    setMonth(context) {
      let monthSel = document.querySelector("#month");
      let monthOpt = '<option value =""></option>';
      for (let i = 1; i < 13; i++) {
        monthOpt += "<option value='" + i + "'>" + i + "월</option>";
      }
      monthSel.innerHTML = monthOpt;
    },
    setSido(context) {
      return apt.get("/selectSido").then(({ data }) => {
        let sidoSel = document.getElementById("sido");
        let sidoOpt = "<option></option>";
        for (let i = 0; i < data.length; i++) {
          sidoOpt += "<option>" + data[i] + "</option>";
        }
        sidoSel.innerHTML = sidoOpt;
      });
    },
    setGugun(context) {
      let sidoSel = document.getElementById("sido");
      console.log(sidoSel.value);
      if (sidoSel.value == "") {
        let gugunSel = document.getElementById("gugun");
        let gugunOpt = "<option></option>";
        gugunSel.innerHTML = gugunOpt;
        return;
      }
      return apt.get("/selectGugun/" + sidoSel.value).then(({ data }) => {
        let gugunSel = document.getElementById("gugun");
        let gugunOpt = "<option></option>";
        for (let i = 0; i < data.length; i++) {
          gugunOpt += "<option>" + data[i] + "</option>";
        }
        gugunSel.innerHTML = gugunOpt;
      });
    },
    setDong(context) {
      let sidoSel = document.getElementById("sido");
      let gugunSel = document.getElementById("gugun");
      console.log(gugunSel.value);
      if (gugunSel.value == "") {
        let dongSel = document.getElementById("dong");
        let dongOpt = "<option></option>";
        dongSel.innerHTML = dongOpt;
        return;
      }
      return apt.get("/selectDong/" + sidoSel.value + "/" + gugunSel.value).then(({ data }) => {
        let dongSel = document.getElementById("dong");
        let dongOpt = "<option></option>";
        for (let i = 0; i < data.length; i++) {
          dongOpt += "<option>" + data[i] + "</option>";
        }
        dongSel.innerHTML = dongOpt;
      });
    },
    searchApt(context, searchInfo) {
      console.log(searchInfo);
      context.commit("SET_SEARCH_INFO", { searchInfo: searchInfo });
    },
    async setAptList({ commit }, { paging, searchInfo }) {
      try {
        let result = await apt.get(
          "/selectAptDetail/" +
          searchInfo.sido +
          "/" +
          searchInfo.gugun +
          "/" +
          searchInfo.dong +
          "?" +
          "nowPage=" +
          paging.nowPage +
          "&" +
          "cntPerPage=" +
          paging.cntPerPage
        );
        commit("SET_APT_LIST", result.data);
        console.log("아파트 목록보기 성공");
      } catch (error) {
        console.log("아파트 목록보기 실패");
      }
    },
    async setDealList({ commit }, { paging, aptCode }) {
      try {
        let result = await apt.get(
          "/dealList/" +
          aptCode +
          "?" +
          "nowPage=" +
          paging.nowPage +
          "&" +
          "cntPerPage=" +
          paging.cntPerPage
        );
        commit("SET_DEAL_LIST", result.data);
        console.log(aptCode + "번 아파트 거래 내역 보기 성공");
      } catch (error) {
        console.log(aptCode + "번 아파트 거래 내역 보기 실패");
      }
    },
    async setAptList2({ commit }, { paging, searchInfo }) {
      try {
        let result;
        result = await apt.get(
          "/selectAptDetail2/" +
          searchInfo.sido +
          "/" +
          searchInfo.gugun +
          "/" +
          searchInfo.dong +
          "/" +
          searchInfo.keyword +
          "?" +
          "nowPage=" +
          paging.nowPage +
          "&" +
          "cntPerPage=" +
          paging.cntPerPage
        );
        commit("SET_APT_LIST", result.data);
        console.log("아파트 목록보기2 성공");
      } catch (error) {
        console.log("아파트 목록보기2 실패");
      }
    },
    async setApt({ commit }, apt) {
      commit("SET_APT", apt);
      console.log("아파트 상세보기 성공");
    },
  },
};

export default aptStore;
