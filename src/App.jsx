import React, { useContext } from "react";
import { Routes, Route, Link } from "react-router-dom";
import Home from "./components/Home";
import Register from "./components/Register";
import Login from "./components/Login";
import UserDashboard from "./components/UserDashboard";
import ProviderDashboard from "./components/ProviderDashboard";
import { UserContext } from "./UserContext";
import "./styles/navbar.css";

function NavBar() {
  const { user, setUser } = useContext(UserContext);

  const logout = () => {
    localStorage.removeItem("currentUser");
    setUser(null);
    window.location.href = "/login";
  };

  return (
    <div className="nav-container">
      <Link to="/" className="nav-logo">Appointment</Link>

      <div className="nav-right">
        {user ? (
          <>
            <span className="nav-user">
              {user.name} <span className="dot">•</span> {user.role}
            </span>
            <button className="nav-btn small red" onClick={logout}>
              Logout
            </button>
          </>
        ) : (
          <>
            <Link to="/login" className="nav-btn small outline">Login</Link>
            <Link to="/register" className="nav-btn small">Register</Link>
          </>
        )}
      </div>
    </div>
  );
}

export default function App() {
  return (
    <>
      <NavBar />
      <div className="main-area">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/register" element={<Register />} />
          <Route path="/login" element={<Login />} />
          <Route path="/user" element={<UserDashboard />} />
          <Route path="/provider" element={<ProviderDashboard />} />
        </Routes>
      </div>
    </>
  );
}
