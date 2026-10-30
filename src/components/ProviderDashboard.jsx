import React, { useEffect, useCallback, useState } from "react";
import { appointmentService } from "../api/appointmentService";
import "../styles/providerDashboard.css";

export default function ProviderDashboard() {
  const current = JSON.parse(localStorage.getItem("currentUser") || "null");

  const [loggedOut, setLoggedOut] = useState(false);
  const [requests, setRequests] = useState([]);

  const providerId = current?.userId;

  const load = useCallback(async () => {
    try {
      const res = await appointmentService.getProviderAppointments(providerId);
      setRequests(res.data || []);
    } catch {
      setRequests([]);
    }
  }, [providerId]);

  useEffect(() => {
    if (!current) {
      setLoggedOut(true);
      return;
    }
    load();
  }, [current, load]);

  const update = async (id, status) => {
    await appointmentService.updateStatus(id, status);
    load();
  };

  if (loggedOut) return <div className="pd-card">Please login first.</div>;

  return (
    <div className="pd-card dashboard">
      <h2 className="pd-title">Provider Dashboard</h2>

      <div className="pd-section">
        <h3 className="pd-section-title">Incoming Requests</h3>

        {requests.length === 0 ? (
          <div className="pd-muted">No pending requests</div>
        ) : (
          <div className="pd-request-list">
            {requests.map((r) => (
              <div className="pd-request-card" key={r.appointmentId}>
                <div className="pd-left">
                  <div className="pd-user">{r.userName}</div>
                  <div className="pd-datetime">
                    {r.appointmentDate} — {r.appointmentTime}
                  </div>
                  <div className={`pd-status ${r.status.toLowerCase()}`}>
                    {r.status}
                  </div>
                </div>

                {r.status === "PENDING" && (
                  <div className="pd-actions">
                    <button
                      onClick={() => update(r.appointmentId, "APPROVED")}
                      className="btn small"
                    >
                      Approve
                    </button>

                    <button
                      onClick={() => update(r.appointmentId, "REJECTED")}
                      className="btn small red"
                    >
                      Reject
                    </button>
                  </div>
                )}
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}
