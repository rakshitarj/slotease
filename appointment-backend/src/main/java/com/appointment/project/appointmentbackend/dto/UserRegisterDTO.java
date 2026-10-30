package com.appointment.project.appointmentbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {
	   @NotBlank
	    private String name;

	    @NotBlank
	    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	    private String mobileNo;

	    @NotBlank
	    @Email(message = "Invalid email format")
	    private String email;

	    @NotBlank
	    @Size(min = 6, message = "Password must be at least 6 characters")
	    private String password;

	    @NotBlank
	    private String role; 
	    private Long categoryId;
		public String getName() {
			return name;
		}
		public String getMobileNo() {
			return mobileNo;
		}
		public String getEmail() {
			return email;
		}
		public String getPassword() {
			return password;
		}
		public String getRole() {
			return role;
		}
		public Long getCategoryId() {
			return categoryId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
}
