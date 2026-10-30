import React, { useEffect, useState, useCallback } from "react";
import { appointmentService } from "../api/appointmentService";
import { categoryService } from "../api/categoryService";
import { providerService } from "../api/providerService";
import "../styles/userDashboard.css";

export default function UserDashboard() {
  const current = JSON.parse(localStorage.getItem("currentUser") || "null");
  const [loggedOut, setLoggedOut] = useState(false);

  const [categories, setCategories] = useState([]);
  const [providers, setProviders] = useState([]);
  const [appointments, setAppointments] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState("");

  const [booking, setBooking] = useState({
    providerId: "",
    appointmentDate: "",
    appointmentTime: "",
  });

  const [error, setError] = useState("");

  const userId = current?.userId;

  const loadCategories = useCallback(async () => {
    try {
      const res = await categoryService.getAllCategories();
      setCategories(res.data || []);
    } catch {
      setCategories([]);
    }
  }, []);

  const loadProviders = useCallback(async (catId) => {
    if (!catId) return setProviders([]);
    try {
      const res = await providerService.getProvidersByCategory(catId);
      setProviders(res.data || []);
    } catch {
      setProviders([]);
    }
  }, []);

  const loadAppointments = useCallback(async () => {
    try {
      const res = await appointmentService.getUserAppointments(userId);
      setAppointments(res.data || []);
    } catch {
      setAppointments([]);
    }
  }, [userId]);

  useEffect(() => {
  if (!userId) {
    setLoggedOut(true);
    return;
  }

  loadCategories();
  loadAppointments();
}, [userId, loadCategories, loadAppointments]);

  const onCategoryChange = (e) => {
    const id = Number(e.target.value);
    setSelectedCategory(id);
    loadProviders(id);
  };

  const book = async () => {
    setError("");

    if (!booking.providerId || !booking.appointmentDate || !booking.appointmentTime) {
      setError("Please select provider, date and time");
      return;
    }

    const payload = {
      appointmentDate: booking.appointmentDate,
      appointmentTime: booking.appointmentTime,
      userId,
      providerId: Number(booking.providerId),
    };

    try {
      await appointmentService.book(payload);
      await loadAppointments();
      setBooking({ providerId: "", appointmentDate: "", appointmentTime: "" });
    } catch {
      setError("Booking failed");
    }
  };

  if (loggedOut) return <div className="ud-card">Please login first.</div>;

  return (
    <div className="ud-card dashboard">

      <h2 className="ud-title">User Dashboard</h2>

      <div className="ud-section">
        <h3 className="ud-section-title">Select Category</h3>
        <select className="input" value={selectedCategory} onChange={onCategoryChange}>
          <option value="">Choose category</option>
          {categories.map((c) => (
            <option key={c.categoryId} value={c.categoryId}>{c.categoryName}</option>
          ))}
        </select>
      </div>

      <div className="ud-section">
        <h3 className="ud-section-title">Available Providers</h3>
        {providers.length === 0 ? (
          <div className="ud-muted">No providers for this category</div>
        ) : (
          <ul className="ud-provider-list">
            {providers.map((p) => (
              <li key={p.providerId} className="ud-provider-item">{p.name}</li>
            ))}
          </ul>
        )}
      </div>

      <div className="ud-section">
        <h3 className="ud-section-title">Book Appointment</h3>

        <div className="ud-booking-grid">

          <select
            className="input"
            value={booking.providerId}
            onChange={(e) =>
              setBooking({ ...booking, providerId: e.target.value })
            }
          >
            <option value="">Select Provider</option>
            {providers.map((p) => (
              <option key={p.providerId} value={p.providerId}>{p.name}</option>
            ))}
          </select>

          <input
            type="date"
            className="input"
            value={booking.appointmentDate}
            onChange={(e) => setBooking({ ...booking, appointmentDate: e.target.value })}
          />

          <input
            type="time"
            className="input"
            value={booking.appointmentTime}
            onChange={(e) => setBooking({ ...booking, appointmentTime: e.target.value })}
          />

          <button className="btn ud-full-btn" onClick={book}>Book Appointment</button>
        </div>

        {error && <div className="ud-error">{error}</div>}
      </div>

      <div className="ud-section">
        <h3 className="ud-section-title">Your Appointments</h3>

        {appointments.length === 0 ? (
          <div className="ud-muted">No appointments yet</div>
        ) : (
          <div className="ud-appointment-list">
            {appointments.map((a) => (
              <div className="ud-appointment-card" key={a.appointmentId}>
                <div>
                  <div className="ud-appt-date">{a.appointmentDate}</div>
                  <div className="ud-appt-time">{a.appointmentTime}</div>
                </div>
                <div className={`ud-status ${a.status.toLowerCase()}`}>{a.status}</div>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
