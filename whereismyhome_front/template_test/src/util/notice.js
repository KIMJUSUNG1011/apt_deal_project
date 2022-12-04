import axios from "axios";
import store from "@/store";
let instance = axios.create({
  baseURL: "http://192.168.130.62:80/home/notice",
});

instance.interceptors.request.use((config) => {
  config.headers["access-token"] = store.state.userStore.token;
  console.log(config);
  return config;
});

export default instance;
