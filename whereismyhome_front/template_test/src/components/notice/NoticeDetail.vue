<template>
  <div>
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">{{ notice.title }}</h1>
              <span class="color-text-a">notice title</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="#">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">
                  Notice &nbsp;/&nbsp; Detail
                </li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->
    <section class="news-single nav-arrow-b">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="news-img-box">
              <img src="assets/img/slide-3.jpg" alt="" class="img-fluid" />
            </div>
          </div>
          <div class="col-md-10 offset-md-1 col-lg-8 offset-lg-2">
            <div class="post-information">
              <ul class="list-inline text-center color-a" style="font-size: 18px">
                <li class="list-inline-item mr-2">
                  <strong>Name : </strong>
                  <span class="color-text-a">{{ notice.name }}</span>
                  &nbsp;
                </li>
                <li class="list-inline-item mr-2">
                  <strong>Email: </strong>
                  <span class="color-text-a">{{ notice.userId }}</span>
                  &nbsp;
                </li>
                <li class="list-inline-item">
                  <strong>Date: </strong>
                  <span class="color-text-a">{{ notice.registerDate }}</span>
                </li>
              </ul>
            </div>
            <div class="post-content color-text-a">
              <p class="post-intro">
                {{ notice.content }}
              </p>
              <p></p>
            </div>
            <div class="post-footer">
              <div class="post-share">
                <span>Share: </span>
                <ul class="list-inline socials">
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-facebook" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-twitter" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-instagram" aria-hidden="true"></i>
                    </a>
                  </li>
                  <li class="list-inline-item">
                    <a href="#">
                      <i class="bi bi-linkedin" aria-hidden="true"></i>
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
          <div class="col-md-10 offset-md-1 col-lg-10 offset-lg-1">
            <div class="title-box-d">
              <h3 class="title-d">Comments (4)</h3>
            </div>
            <div class="box-comments">
              <ul class="list-comments">
                <li>
                  <div class="comment-avatar">
                    <img src="assets/img/author-2.jpg" alt="" />
                  </div>
                  <div class="comment-details">
                    <h4 class="comment-author">김주성</h4>
                    <span>2022 11 24</span>
                    <p class="comment-description">리뷰 기능은 아직 동작하지 않습니다.</p>
                    <a href="3">Reply</a>
                  </div>
                </li>
                <!-- <li class="comment-children">
                  <div class="comment-avatar">
                    <img src="assets/img/author-1.jpg" alt="" />
                  </div>
                  <div class="comment-details">
                    <h4 class="comment-author">Oliver Colmenares</h4>
                    <span>18 Sep 2017</span>
                    <p class="comment-description">
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Dolores
                      reprehenderit, provident cumque ipsam temporibus maiores quae.
                    </p>
                    <a href="3">Reply</a>
                  </div>
                </li> -->
              </ul>
            </div>
            <div class="form-comments">
              <div class="title-box-d">
                <h3 class="title-d">Leave a Reply</h3>
              </div>
              <form class="form-a">
                <div class="row">
                  <div class="col-md-6 mb-3">
                    <div class="form-group">
                      <label for="inputName">Enter name</label>
                      <input
                        type="text"
                        class="form-control form-control-lg form-control-a"
                        id="inputName"
                        placeholder="Name *"
                        required
                      />
                    </div>
                  </div>
                  <div class="col-md-6 mb-3">
                    <div class="form-group">
                      <label for="inputEmail1">Enter email</label>
                      <input
                        type="email"
                        class="form-control form-control-lg form-control-a"
                        id="inputEmail1"
                        placeholder="Email *"
                        required
                      />
                    </div>
                  </div>
                  <div class="col-md-12 mb-3">
                    <div class="form-group">
                      <label for="inputUrl">Enter website</label>
                      <input
                        type="url"
                        class="form-control form-control-lg form-control-a"
                        id="inputUrl"
                        placeholder="Website"
                      />
                    </div>
                  </div>
                  <div class="col-md-12 mb-3">
                    <div class="form-group">
                      <label for="textMessage">Enter message</label>
                      <textarea
                        id="textMessage"
                        class="form-control"
                        placeholder="Comment *"
                        name="message"
                        cols="45"
                        rows="8"
                        required
                      ></textarea>
                    </div>
                  </div>
                  <div class="col-md-12">
                    <button type="submit" class="btn btn-a">Send Message</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      notice: {},
    };
  },
  async created() {
    this.notice = this.$store.state.noticeStore.notice;
  },
};
</script>

<style></style>
