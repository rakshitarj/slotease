package com.appointment.project.appointmentbackend.dto;

public class ProviderResponseDTO {
	private Long providerId;
    private String name;
    private String email;
    private String mobileNo;
    private Long categoryId;
    private String categoryName;
	public Long getProviderId() {
		return providerId;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setProviderId(Long providerId) {
		this.providerId = providerId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ProviderResponseDTO(Long providerId, String name, String email, String mobileNo) {
		super();
		this.providerId = providerId;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	
}
