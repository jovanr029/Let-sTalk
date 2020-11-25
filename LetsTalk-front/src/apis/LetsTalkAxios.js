import axios from "axios";

const accessToken = window.localStorage.getItem("token");

var LetsTalkAxios = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    Authorization: `Bearer ${accessToken}`,
  },
});

export default LetsTalkAxios;
