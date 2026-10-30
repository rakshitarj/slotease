import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { authService } from "../api/authService";
import { UserContext } from "../UserContext";
import "../styles/login.css";


export default function Login() {
  const nav = useNavigate();
  const { setUser } = useContext(UserContext);

  const [form, setForm] = useState({ email: "", password: "" });
  const [err, setErr] = useState("");

  useEffect(() => {
    const u = JSON.parse(localStorage.getItem("currentUser") || "null");
    if (u) {
      const role = u.role?.toUpperCase();
      if (role === "PROVIDER") nav("/provider");
      else nav("/user");
    }
  }, [nav]);

  const submit = async (e) => {
    e.preventDefault();
    setErr("");

    try {
      const res = await authService.login(form);
      const user = res.data;
setUser(user);
localStorage.setItem("currentUser", JSON.stringify(user));


      const role = user.role?.toUpperCase();
      if (role === "PROVIDER") nav("/provider");
      else nav("/user");

    } catch (error) {
      setErr(error.response?.data || "Login failed");
    }
  };

  return (
    <div className="login-wrapper">
      <div className="login-card">

        <h2 className="login-title">Welcome Back</h2>
        <p className="login-sub">Login to continue</p>

        {err && <div className="login-error">{err}</div>}

        <form onSubmit={submit}>

          <div className="input-group">
            <label>Email</label>
            <input
              className="input"
              type="email"
              value={form.email}
              onChange={(e) => setForm({ ...form, email: e.target.value })}
              placeholder="you@example.com"
            />
          </div>

          <div className="input-group">
            <label>Password</label>
            <input
              type="password"
              className="input"
              value={form.password}
              onChange={(e) => setForm({ ...form, password: e.target.value })}
              placeholder="Enter password"
            />
          </div>

          <button className="btn login-btn" type="submit">
            Login
          </button>
        </form>

        <div className="login-register-link">
          Don't have an account? <span onClick={() => nav("/register")}>Register</span>
        </div>

      </div>
    </div>
  );
}
