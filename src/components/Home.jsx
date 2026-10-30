import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/home.css";


export default function Home() {
  const nav = useNavigate();

  return (
    <div className="home-wrapper">

      <div className="home-card">
        <h1 className="home-title">Book Appointments Easily</h1>

        <p className="home-text">
          Manage your bookings, connect with providers, and schedule your
          appointments effortlessly.
        </p>

        <div className="home-buttons">
          <button className="btn home-btn" onClick={() => nav("/login")}>
            Login
          </button>

          <button
            className="btn outline home-btn"
            onClick={() => nav("/register")}
          >
            Register
          </button>
        </div>
      </div>

    </div>
  );
}
