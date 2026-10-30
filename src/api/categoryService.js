import axios from "axios";
import { API_BASE } from "./apiConfig";

const BASE = `${API_BASE}/provider`;

export const categoryService = {
  getAllCategories: () => axios.get(`${BASE}/categories`),
  addCategory: (name) =>
    axios.post(`${BASE}/add`, name, {
      headers: { "Content-Type": "text/plain" },
    }),
};
