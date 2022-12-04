import axios from "axios";

let instance = axios.create({
  baseURL: "http://192.168.130.62:80/home/apt",
});

export default instance;
