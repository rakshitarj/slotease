package com.appointment.project.appointmentbackend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

public class AppointmentRequestDTO {
	  @NotNull
	    private LocalDate appointmentDate;

	    @NotNull
	    private LocalTime appointmentTime;

	    @NotNull
	    private Long userId;

	    @NotNull
	    private Long providerId;

		public LocalDate getAppointmentDate() {
			return appointmentDate;
		}

		public LocalTime getAppointmentTime() {
			return appointmentTime;
		}

		public Long getUserId() {
			return userId;
		}

		public Long getProviderId() {
			return providerId;
		}

		public void setAppointmentDate(LocalDate appointmentDate) {
			this.appointmentDate = appointmentDate;
		}

		public void setAppointmentTime(LocalTime appointmentTime) {
			this.appointmentTime = appointmentTime;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public void setProviderId(Long providerId) {
			this.providerId = providerId;
		}
}
