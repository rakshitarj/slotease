import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080/api/user",
  timeout: 10000,
  headers: { "Content-Type": "application/json" },
});

export const authService = {
  register: (payload) => API.post("/register", payload),
  login: (payload) => API.post("/login", payload),
};
