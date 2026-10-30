package com.appointment.project.appointmentbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentResponseDTO {
	private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String status;
    private Long userId;
    private String userName;
    private Long providerId;
    private String providerName;
	public Long getAppointmentId() {
		return appointmentId;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public String getStatus() {
		return status;
	}
	public Long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public Long getProviderId() {
		return providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
}
