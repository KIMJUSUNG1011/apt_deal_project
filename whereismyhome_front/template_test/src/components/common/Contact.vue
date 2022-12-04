<template>
  <div>
    <!-- ======= Intro Single ======= -->
    <section class="intro-single">
      <div class="container">
        <div class="row">
          <div class="col-md-12 col-lg-8">
            <div class="title-single-box">
              <h1 class="title-single">Contact US</h1>
              <span class="color-text-a">운영진에게 자유롭게 의견을 보내주세요</span>
            </div>
          </div>
          <div class="col-md-12 col-lg-4">
            <nav aria-label="breadcrumb" class="breadcrumb-box d-flex justify-content-lg-end">
              <ol class="breadcrumb">
                <li class="breadcrumb-item">
                  <a href="index.html">Home</a>
                </li>
                <li class="breadcrumb-item active" aria-current="page">Contact</li>
              </ol>
            </nav>
          </div>
        </div>
      </div>
    </section>
    <!-- End Intro Single-->

    <!-- ======= Contact Single ======= -->
    <section class="contact">
      <div class="container">
        <div class="row">
          <div class="col-sm-12">
            <div class="contact-map box">
              <div id="map" class="contact-map">
                <iframe
                  src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3213.2393060054096!2d127.29615161556416!3d36.35498200033304!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35654cad3d82caa9%3A0xb3fbce2dcaf4b67f!2z7IK87ISx7ZmU7J6sIOycoOyEseyXsOyImOybkA!5e0!3m2!1sko!2skr!4v1669178821370!5m2!1sko!2skr"
                  width="100%"
                  height="450"
                  frameborder="0"
                  style="border: 0"
                  allowfullscreen=""
                ></iframe>
              </div>
            </div>
          </div>
          <div class="col-sm-12 section-t8">
            <div class="row">
              <div class="col-md-7">
                <form class="php-email-form">
                  <div class="row">
                    <div class="col-md-6 mb-3">
                      <div class="form-group">
                        <input
                          v-model="name"
                          type="text"
                          name="name"
                          class="form-control form-control-lg form-control-a"
                          placeholder="Your Name"
                          required
                        />
                      </div>
                    </div>
                    <div class="col-md-6 mb-3">
                      <div class="form-group">
                        <input
                          v-model="email"
                          name="email"
                          type="email"
                          class="form-control form-control-lg form-control-a"
                          placeholder="Your Email"
                          required
                        />
                      </div>
                    </div>
                    <div class="col-md-12 mb-3">
                      <div class="form-group">
                        <input
                          v-model="subject"
                          type="text"
                          name="subject"
                          class="form-control form-control-lg form-control-a"
                          placeholder="Subject"
                          required
                        />
                      </div>
                    </div>
                    <div class="col-md-12">
                      <div class="form-group">
                        <textarea
                          v-model="message"
                          name="message"
                          class="form-control"
                          cols="45"
                          rows="8"
                          placeholder="Message"
                          required
                        ></textarea>
                      </div>
                    </div>
                    <div class="col-md-12 text-center" style="margin-top: 20px">
                      <button @click="sendEmail()" type="button" class="btn btn-a">
                        Send Message
                      </button>
                    </div>
                  </div>
                </form>
              </div>
              <div class="col-md-5 section-md-t3">
                <div class="icon-box section-b2">
                  <div class="icon-box-icon">
                    <span class="bi bi-envelope"></span>
                  </div>
                  <div class="icon-box-content table-cell">
                    <div class="icon-box-title">
                      <h4 class="icon-title">Say Hello</h4>
                    </div>
                    <div class="icon-box-content">
                      <p class="mb-1">
                        Email.
                        <span class="color-a">dlrtls12345@naver.com</span>
                      </p>
                      <p class="mb-1">
                        Phone.
                        <span class="color-a">010-5292-7836</span>
                      </p>
                    </div>
                  </div>
                </div>
                <div class="icon-box section-b2">
                  <div class="icon-box-icon">
                    <span class="bi bi-geo-alt"></span>
                  </div>
                  <div class="icon-box-content table-cell">
                    <div class="icon-box-title">
                      <h4 class="icon-title">Find us in</h4>
                    </div>
                    <div class="icon-box-content">
                      <p class="mb-1">
                        대전광역시 유성구, 삼성화재 연수원,
                        <br />
                        대한민국
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Contact Single-->
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      name: "",
      email: "",
      subject: "",
      message: "",
    };
  },
  methods: {
    sendEmail() {
      let emailData = {
        name: this.name,
        email: this.email,
        subject: this.subject,
        message: this.message,
      };
      axios.post("http://192.168.130.62:80/home/user/email", emailData);
      console.log("이메일 등록 성공");
      alert("이메일 전송이 완료되었습니다.");
      location.reload();
    },
  },
};
</script>

<style></style>
