import axios from "axios";

import store from "@/store";

let instance = axios.create({
  baseURL: "http://192.168.130.62:80",
});

instance.interceptors.request.use((config) => {
  console.log(config);
  config.headers["access-token"] = store.state.token;
  return config;
});

export default instance;
