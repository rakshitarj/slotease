import axios from "axios";
import { API_BASE } from "./apiConfig";

// fetching providers by category: GET /user/providers/{categoryId}
export const providerService = {
  getProvidersByCategory: (categoryId) => axios.get(`${API_BASE}/user/providers/${categoryId}`),
};
