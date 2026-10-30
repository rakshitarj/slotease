package com.appointment.project.appointmentbackend.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "provider_categories")
public class ProviderCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(unique = true)
    private String categoryName; 

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<User> providers;

 
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public List<User> getProviders() { return providers; }
    public void setProviders(List<User> providers) { this.providers = providers; }

   
    @Override
    public String toString() {
        return "ProviderCategory [categoryId=" + categoryId +", categoryName=" + categoryName + "]";
    }

 
    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ProviderCategory other = (ProviderCategory) obj;
        return Objects.equals(categoryId, other.categoryId);
    }
}
