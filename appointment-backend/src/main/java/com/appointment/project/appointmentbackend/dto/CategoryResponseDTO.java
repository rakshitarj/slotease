package com.appointment.project.appointmentbackend.dto;

public class CategoryResponseDTO {
	
	 private Long categoryId;
	 
	    private String categoryName;
	    
		public Long getCategoryId() {
			return categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public CategoryResponseDTO(Long categoryId, String categoryName) {
			super();
			this.categoryId = categoryId;
			this.categoryName = categoryName;
		}
		
}
