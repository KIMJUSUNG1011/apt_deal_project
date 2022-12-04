import notice from "@/util/notice";
const noticeStore = {
  namespaced: true,
  state: {
    notices: [],
    paging: {},
    notice: {},
  },
  mutations: {
    SET_NOTICE(state, payload) {
      state.notice = payload;
    },
    SET_NOTICE_LIST(state, payload) {
      state.paging = payload.paging;
      state.notices = payload.viewAll;
    },
  },
  actions: {
    async setNotice({ commit }, noticeIdx) {
      try {
        let { data } = await notice.get("/getDetail/" + noticeIdx);
        commit("SET_NOTICE", data);
        console.log("공지사항 상세보기 성공");
      } catch (error) {
        console.log("공지사항 상세보기 실패");
      }
    },
    async setNoticeList({ commit }, paging) {
      try {
        let result = await notice.get(
          "http://192.168.130.62:80/home/notice/list?nowPage=" +
          paging.nowPage +
          "&cntPerPage=" +
          paging.cntPerPage
        );
        commit("SET_NOTICE_LIST", result.data);
        console.log("공지사항 목록 성공");
      } catch (error) {
        console.log("공지사항 목록 실패");
      }
    },
    async registNotice({ commit }, { title, content, userIdx }) {
      try {
        await notice.post("http://192.168.130.62:80/home/notice", {
          title: title,
          content: content,
          userIdx: userIdx
        });
        console.log("공지사항 등록 성공");
        alert("공지사항 등록 성공");
      } catch (error) {
        console.log("공지사항 등록 실패");
        alert("공지사항 등록 실패");
      }
    },
    async updateNotice({ commit }, { noticeIdx, title, content }) {
      try {
        let result = await notice.put("http://192.168.130.62:80/home/notice/" + noticeIdx, {
          title: title,
          content: content,
        });
        console.log("공지사항 수정 성공");
        alert("공지사항 수정 성공");
      } catch (error) {
        console.log("공지사항 수정 실패");
        alert("공지사항 수정 실패");
      }
    },
    async deleteNotice({ commit }, noticeIdx) {
      try {
        let result = await notice.delete("http://192.168.130.62:80/home/notice/" + noticeIdx);
        console.log("공지사항 삭제 성공");
        alert("공지사항 삭제 성공");
      } catch (error) {
        console.log("공지사항 삭제 실패");
        alert("공지사항 삭제 실패");
      }
    },
  },
};

export default noticeStore;
