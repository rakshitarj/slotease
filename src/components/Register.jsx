import React, { useEffect, useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { authService } from "../api/authService";
import axios from "axios";
import { UserContext } from "../UserContext";
import "../styles/register.css";

export default function Register() {
  const nav = useNavigate();
  const { setUser } = useContext(UserContext);

  const [form, setForm] = useState({
    name: "",
    mobileNo: "",
    email: "",
    password: "",
    role: "user",
    categoryId: ""
  });

  const [categories, setCategories] = useState([]);
  const [loadingCategories, setLoadingCategories] = useState(false);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    if (form.role !== "provider") {
      if (form.categoryId) {
        setForm(prev => ({ ...prev, categoryId: "" }));
      }
      return;
    }

    const load = async () => {
      setLoadingCategories(true);
      try {
        const res = await axios.get("http://localhost:8080/api/provider/categories");
        setCategories(res.data || []);
      } catch {
        setCategories([]);
      } finally {
        setLoadingCategories(false);
      }
    };

    load();
  }, [form.role]);

  const handle = (key, value) => {
    setForm(prev => ({ ...prev, [key]: value }));
    setError("");
  };

  const validate = () => {
    if (!form.name.trim() || !form.mobileNo.trim() || !form.email.trim() || !form.password) {
      setError("Please fill all required fields.");
      return false;
    }

    if (!/^[0-9]{10}$/.test(form.mobileNo.trim())) {
      setError("Mobile number must be 10 digits.");
      return false;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(form.email.trim())) {
      setError("Invalid email format.");
      return false;
    }

    if (form.role === "provider" && !form.categoryId) {
      setError("Please select a category.");
      return false;
    }

    return true;
  };

  const submit = async (e) => {
    e.preventDefault();
    setError("");

    if (!validate()) return;

    const payload = {
      name: form.name.trim(),
      mobileNo: form.mobileNo.trim(),
      email: form.email.trim(),
      password: form.password,
      role: form.role.toUpperCase(),
      categoryId: form.role === "provider" ? Number(form.categoryId) : null
    };

    setSubmitting(true);
    try {
      const res = await authService.register(payload);
      const user = res.data;

      setUser(user);
      localStorage.setItem("currentUser", JSON.stringify(user));

      if (user.role === "PROVIDER") nav("/provider");
      else nav("/user");

    } catch (err) {
      setError(err.response?.data?.message || err.message || "Registration failed");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="register-wrapper">
      <div className="register-card">

        <h2 className="register-title">Create Your Account</h2>
        <p className="register-sub">Join and manage your appointments with ease</p>

        {error && <div className="register-error">{error}</div>}

        <form onSubmit={submit}>

          <div className="input-group">
            <label>Name</label>
            <input
              className="input"
              value={form.name}
              onChange={(e) => handle("name", e.target.value)}
              placeholder="Your full name"
            />
          </div>

          <div className="input-group">
            <label>Mobile (10 digits)</label>
            <input
              className="input"
              value={form.mobileNo}
              onChange={(e) => handle("mobileNo", e.target.value)}
              placeholder="9876543210"
            />
          </div>

          <div className="input-group">
            <label>Email</label>
            <input
              className="input"
              type="email"
              value={form.email}
              onChange={(e) => handle("email", e.target.value)}
              placeholder="you@example.com"
            />
          </div>

          <div className="input-group">
            <label>Password</label>
            <input
              className="input"
              type="password"
              value={form.password}
              onChange={(e) => handle("password", e.target.value)}
              placeholder="Minimum 6 characters"
            />
          </div>

          <div className="input-group">
            <label>Role</label>
            <div className="role-row">
              <label>
                <input
                  type="radio"
                  checked={form.role === "user"}
                  onChange={() => handle("role", "user")}
                />
                User
              </label>

              <label>
                <input
                  type="radio"
                  checked={form.role === "provider"}
                  onChange={() => handle("role", "provider")}
                />
                Provider
              </label>
            </div>
          </div>

          {form.role === "provider" && (
            <div className="input-group">
              <label>Category</label>

              {loadingCategories ? (
                <div className="muted">Loading categories...</div>
              ) : (
                <select
                  className="input"
                  value={form.categoryId}
                  onChange={(e) => handle("categoryId", e.target.value)}
                >
                  <option value="">-- choose category --</option>
                  {categories.map(c => (
                    <option key={c.categoryId} value={c.categoryId}>
                      {c.categoryName}
                    </option>
                  ))}
                </select>
              )}
            </div>
          )}

          <button className="btn register-btn" type="submit" disabled={submitting}>
            {submitting ? "Creating..." : "Register"}
          </button>
        </form>
      </div>
    </div>
  );
}
