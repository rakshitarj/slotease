package com.appointment.project.appointmentbackend.dto;


public class UserResponseDTO {
	 private Long userId;
	    private String name;
	    private String email;
	    private String role;
	    private String categoryName;
		public Long getUserId() {
			return userId;
		}
		public String getName() {
			return name;
		}
		public String getEmail() {
			return email;
		}
		public String getRole() {
			return role;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public void setRole(String role) {
			this.role = role;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public UserResponseDTO(Long userId, String name, String email, String role, String categoryName) {
			super();
			this.userId = userId;
			this.name = name;
			this.email = email;
			this.role = role;
			this.categoryName = categoryName;
		}
} 
