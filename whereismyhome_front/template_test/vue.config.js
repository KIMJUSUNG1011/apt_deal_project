const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: {
      // proxyTable 설정
      "/v1": {
        // /api가 아닌 API요청 도메인의 바로 다음 녀석인 /v1으로 url을 검사함
        target: "https://openapi.naver.com/",
        changeOrigin: true,
      },
    },
  },
});

