<template>
  <div class="container-xl" style="margin-top: 200px">
    <div class="table-responsive">
      <div class="table-wrapper">
        <div class="table-title" style="background-color: yellowgreen">
          <div class="row">
            <div class="col-sm-6">
              <h2>Lastest <b>Notices</b></h2>
            </div>
            <div class="col-sm-6">
              <b-button
                v-if="$store.state.userStore.user.id == 'admin@naver.com'"
                class="btn btn-success"
                v-b-modal.modal-regist
              >
                <i class="material-icons">&#xE147;</i> <span>Add New Notice</span>
              </b-button>
              <!-- 공지사항 등록 -->
              <b-modal id="modal-regist" title="Add New Notice" hide-footer>
                <div class="row">
                  <div class="col-md-12 mb-3">
                    <div class="form-group">
                      <input
                        v-model="title"
                        type="text"
                        name="subject"
                        class="form-control"
                        placeholder="Subject"
                        required=""
                      />
                    </div>
                  </div>
                  <div class="col-md-12">
                    <div class="form-group">
                      <textarea
                        v-model="content"
                        name="message"
                        class="form-control"
                        cols="45"
                        rows="8"
                        placeholder="Message"
                        required=""
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-md-12 text-center" style="margin-top: 20px">
                    <button type="button" class="btn btn-a" @click="registNotice()">Submit</button>
                  </div>
                </div>
              </b-modal>
              <!-- 공지사항 수정 -->
              <b-modal id="modal-update" title="Update Notice" hide-footer>
                <div class="row">
                  <div class="col-md-12 mb-3">
                    <div class="form-group">
                      <input
                        v-model="title"
                        type="text"
                        name="subject"
                        class="form-control"
                        :placeholder="this.selectedNotice.title"
                        required=""
                      />
                    </div>
                  </div>
                  <div class="col-md-12">
                    <div class="form-group">
                      <textarea
                        v-model="content"
                        name="message"
                        class="form-control"
                        cols="45"
                        rows="8"
                        :placeholder="this.selectedNotice.content"
                        required=""
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-md-12 text-center" style="margin-top: 20px">
                    <button type="button" class="btn btn-a" @click="updateNotice()">Update</button>
                  </div>
                </div>
              </b-modal>
            </div>
          </div>
        </div>
        <table class="table table-striped table-hover">
          <thead>
            <tr>
              <th>
                <span class="custom-checkbox">
                  <input type="checkbox" id="selectAll" />
                  <label for="selectAll"></label>
                </span>
              </th>
              <th style="width: 10%">#</th>
              <th style="width: 20%">Email</th>
              <th style="width: 20%">Name</th>
              <th style="width: 20%">Title</th>
              <th style="width: 15%">RegisterDate</th>
              <th style="width: 15%; text-align: center">Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr style="cursor: pointer" v-for="(notice, index) in notices" v-bind:key="index">
              <td>
                <span class="custom-checkbox">
                  <input type="checkbox" id="checkbox5" name="options[]" value="1" />
                  <label for="checkbox5"></label>
                </span>
              </td>
              <td @click="goDetail(notice.noticeIdx)">{{ notice.noticeIdx }}</td>
              <td @click="goDetail(notice.noticeIdx)">{{ notice.userId }}</td>
              <td @click="goDetail(notice.noticeIdx)">{{ notice.name }}</td>
              <td @click="goDetail(notice.noticeIdx)">{{ notice.title }}</td>
              <td @click="goDetail(notice.noticeIdx)">{{ notice.registerDate }}</td>
              <td style="text-align: center">
                <i
                  v-if="$store.state.userStore.user.id != 'admin@naver.com'"
                  style="font-size: 17px; text-align: center"
                  >admin only</i
                >
                <i
                  v-if="$store.state.userStore.user.id == 'admin@naver.com'"
                  v-b-modal.modal-update
                  @click="sendInfo(notice)"
                  class="material-icons"
                  data-toggle="tooltip"
                  title="Edit"
                  style="color: goldenrod"
                  >&#xE254;</i
                >
                <a
                  v-if="$store.state.userStore.user.id == 'admin@naver.com'"
                  href="#"
                  class="delete"
                  data-toggle="modal"
                  ><i
                    @click="deleteNotice(notice)"
                    class="material-icons"
                    data-toggle="tooltip"
                    title="Delete"
                    >&#xE872;</i
                  ></a
                >
              </td>
            </tr>
          </tbody>
        </table>
        <div class="clearfix">
          <div class="hint-text">admin-id : <b>admin@naver.com, </b>admin-pass : <b>123</b></div>
          <nav class="pagination-a">
            <ul class="pagination">
              <li
                v-for="(num, index) in list()"
                :class="{ 'page-item active': paging.nowPage == num }"
                v-bind:key="index"
              >
                <div class="page-link" @click="setting" ref="getPageNum" v-html="num"></div>
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 페이징 처리 알고리즘
// 상단의 Notice 클릭 시 1 페이지와 아래 페이지 숫자들이 나와야함
// 하단의 페이지 숫자를 누를 때마다 함수가 호출되어 에이작스 요청을 날려야함
// 에이작스 요청을 날릴 때마다 paging 정보를 리턴받아 data 에 저장
// 해당 정보로 아래 페이지 숫자들을 세팅함

export default {
  data() {
    return {
      paging: {},
      notices: [],
      selectedNotice: {},
      title: "",
      content: "",
    };
  },
  // 공지사항 리스트 로딩 시 우선 store 의 페이징 객체를 확인함
  async created() {
    let tmpPaging = await this.$store.state.noticeStore.paging;
    let initPagingData = { nowPage: "1", cntPerPage: "5" };
    if (tmpPaging === undefined || tmpPaging.nowPage == null) {
      await this.$store.dispatch("noticeStore/setNoticeList", initPagingData);
    } else {
      await this.$store.dispatch("noticeStore/setNoticeList", tmpPaging);
    }
    this.notices = this.$store.state.noticeStore.notices;
    this.paging = this.$store.state.noticeStore.paging;
  },
  methods: {
    list() {
      let list = [];
      if (this.paging.startPage != 1) {
        list.push("&lt");
      }
      for (let i = this.paging.startPage; i <= this.paging.endPage; i++) {
        list.push(i);
      }
      if (this.paging.endPage != this.paging.lastPage) {
        list.push("&gt");
      }
      return list;
    },
    async setting(e) {
      let nowPage = e.target.innerHTML;
      this.paging.nowPage = nowPage;
      if (nowPage == "&lt;") {
        this.paging.nowPage = this.paging.startPage - 1;
      }
      if (nowPage == "&gt;") {
        this.paging.nowPage = this.paging.endPage + 1;
      }
      await this.$store.dispatch("noticeStore/setNoticeList", this.paging);
      this.notices = this.$store.state.noticeStore.notices;
      this.paging = this.$store.state.noticeStore.paging;
    },
    sendInfo(notice) {
      this.selectedNotice = notice;
    },
    async goDetail(noticeIdx) {
      await this.$store.dispatch("noticeStore/setNotice", noticeIdx);
      this.$router.push({ name: "noticeDetail" });
    },
    async registNotice() {
      await this.$store.dispatch("noticeStore/registNotice", {
        title: this.title,
        content: this.content,
        userIdx: this.$store.state.userStore.user.idx,
      });
      this.$bvModal.hide("modal-regist");
      location.reload();
    },
    async updateNotice() {
      await this.$store.dispatch("noticeStore/updateNotice", {
        noticeIdx: this.selectedNotice.noticeIdx,
        title: this.title,
        content: this.content,
      });
      this.$bvModal.hide("modal-update");
      location.reload();
    },
    async deleteNotice(notice) {
      if (confirm("정말 삭제하시겠습니까?")) {
        await this.$store.dispatch("noticeStore/deleteNotice", notice.noticeIdx);
        location.reload();
      }
    },
  },
};
</script>

<style scoped>
.modal-header {
  border-bottom: 0 none;
}

.modal-footer {
  border-top: 0 none;
}

.form-group textarea.form-control {
  height: auto;
}
table {
  table-layout: fixed;
}
.page-link:hover {
  cursor: pointer;
}
table {
  table-layout: fixed;
}
body {
  color: #566787;
  background: #f5f5f5;
  font-family: "Varela Round", sans-serif;
  font-size: 13px;
}
.table-responsive {
  margin: 30px 0;
}
.table-wrapper {
  background: #fff;
  padding: 20px 25px;
  border-radius: 3px;
  min-width: 1000px;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.05);
}
.table-title {
  padding-bottom: 15px;
  background: #435d7d;
  color: #fff;
  padding: 16px 30px;
  min-width: 100%;
  margin: -20px -25px 10px;
  border-radius: 3px 3px 0 0;
}
.table-title h2 {
  margin: 5px 0 0;
  font-size: 24px;
}
.table-title .btn-group {
  float: right;
}
.table-title .btn {
  color: #fff;
  float: right;
  font-size: 13px;
  border: none;
  min-width: 50px;
  border-radius: 2px;
  border: none;
  outline: none !important;
  margin-left: 10px;
}
.table-title .btn i {
  float: left;
  font-size: 21px;
  margin-right: 5px;
}
.table-title .btn span {
  float: left;
  margin-top: 2px;
}
table.table tr th,
table.table tr td {
  border-color: #e9e9e9;
  padding: 12px 15px;
  vertical-align: middle;
}
table.table tr th:first-child {
  width: 60px;
}
table.table tr th:last-child {
  width: 100px;
}
table.table-striped tbody tr:nth-of-type(odd) {
  background-color: #fcfcfc;
}
table.table-striped.table-hover tbody tr:hover {
  background: #f5f5f5;
}
table.table th i {
  font-size: 13px;
  margin: 0 5px;
  cursor: pointer;
}
table.table td:last-child i {
  opacity: 0.9;
  font-size: 22px;
  margin: 0 5px;
}
table.table td a {
  font-weight: bold;
  color: #566787;
  display: inline-block;
  text-decoration: none;
  outline: none !important;
}
table.table td a:hover {
  color: #2196f3;
}
table.table td a.edit {
  color: #ffc107;
}
table.table td a.delete {
  color: #f44336;
}
table.table td i {
  font-size: 19px;
}
table.table .avatar {
  border-radius: 50%;
  vertical-align: middle;
  margin-right: 10px;
}
.pagination {
  float: right;
  margin: 0 0 5px;
}
.pagination li a {
  border: none;
  font-size: 13px;
  min-width: 30px;
  min-height: 30px;
  color: #999;
  margin: 0 2px;
  line-height: 30px;
  border-radius: 2px !important;
  text-align: center;
  padding: 0 6px;
}
.pagination li a:hover {
  color: #666;
}
.pagination li.active a,
.pagination li.active a.page-link {
  background: #03a9f4;
}
.pagination li.active a:hover {
  background: #0397d6;
}
.pagination li.disabled i {
  color: #ccc;
}
.pagination li i {
  font-size: 16px;
  padding-top: 6px;
}
.hint-text {
  float: left;
  margin-top: 10px;
  font-size: 13px;
}
/* Custom checkbox */
.custom-checkbox {
  position: relative;
}
.custom-checkbox input[type="checkbox"] {
  opacity: 0;
  position: absolute;
  margin: 5px 0 0 3px;
  z-index: 9;
}
.custom-checkbox label:before {
  width: 18px;
  height: 18px;
}
.custom-checkbox label:before {
  content: "";
  margin-right: 10px;
  display: inline-block;
  vertical-align: text-top;
  background: white;
  border: 1px solid #bbb;
  border-radius: 2px;
  box-sizing: border-box;
  z-index: 2;
}
.custom-checkbox input[type="checkbox"]:checked + label:after {
  content: "";
  position: absolute;
  left: 6px;
  top: 3px;
  width: 6px;
  height: 11px;
  border: solid #000;
  border-width: 0 3px 3px 0;
  transform: inherit;
  z-index: 3;
  transform: rotateZ(45deg);
}
.custom-checkbox input[type="checkbox"]:checked + label:before {
  border-color: #03a9f4;
  background: #03a9f4;
}
.custom-checkbox input[type="checkbox"]:checked + label:after {
  border-color: #fff;
}
.custom-checkbox input[type="checkbox"]:disabled + label:before {
  color: #b8b8b8;
  cursor: auto;
  box-shadow: none;
  background: #ddd;
}
/* Modal styles */
.modal .modal-dialog {
  max-width: 400px;
}
.modal .modal-header,
.modal .modal-body,
.modal .modal-footer {
  padding: 20px 30px;
}
.modal .modal-content {
  border-radius: 3px;
  font-size: 14px;
}
.modal .modal-footer {
  background: #ecf0f1;
  border-radius: 0 0 3px 3px;
}
.modal .modal-title {
  display: inline-block;
}
.modal .form-control {
  border-radius: 2px;
  box-shadow: none;
  border-color: #dddddd;
}
.modal textarea.form-control {
  resize: vertical;
}
.modal .btn {
  border-radius: 2px;
  min-width: 100px;
}
.modal form label {
  font-weight: normal;
}
</style>
