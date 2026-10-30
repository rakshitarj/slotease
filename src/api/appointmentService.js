import axios from "axios";
import { API_BASE } from "./apiConfig";

const BASE = `${API_BASE}/appointment`;

export const appointmentService = {
  // POST /appointment/book  with AppointmentRequestDTO
  book: (payload) => axios.post(`${BASE}/book`, payload),

  // GET /appointment/user/{userId}
  getUserAppointments: (userId) => axios.get(`${BASE}/user/${userId}`),

  // GET /appointment/provider/{providerId}
  getProviderAppointments: (providerId) => axios.get(`${BASE}/provider/${providerId}`),

  // PUT /appointment/status/{id}?status=...
  updateStatus: (id, status) => axios.put(`${BASE}/status/${id}`, null, { params: { status } }),
};
