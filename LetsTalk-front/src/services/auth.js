import LetsTalkAxios from "../apis/LetsTalkAxios";

export const login = async function (credentials) {
  try {
    let response = await LetsTalkAxios.post("/users/login", credentials);
    let token = response.data;

    console.log(token);
    window.localStorage.setItem("token", token);

    window.location.reload();
  } catch (error) {
    alert("could not log in");
  }
};

export const logout = function () {
  window.localStorage.removeItem("token");
  window.location.reload();
};
