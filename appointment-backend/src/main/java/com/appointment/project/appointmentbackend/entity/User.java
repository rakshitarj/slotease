package com.appointment.project.appointmentbackend.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$")
    private String mobileNo;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6)
    @Pattern(regexp = ".*[!@#$%^&*()_+].*")
    private String password;

    @NotBlank
    private String role; 
    

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProviderCategory category; 

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Appointment> bookedAppointments;
    
    @OneToMany(mappedBy = "provider")
    @JsonIgnore
    private List<Appointment> receivedAppointments;

  

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public ProviderCategory getCategory() { return category; }
    public void setCategory(ProviderCategory category) { this.category = category; }
    public List<Appointment> getBookedAppointments() { return bookedAppointments; }
    public void setBookedAppointments(List<Appointment> bookedAppointments) { this.bookedAppointments = bookedAppointments; }

    public List<Appointment> getReceivedAppointments() { return receivedAppointments; }
    public void setReceivedAppointments(List<Appointment> receivedAppointments) { this.receivedAppointments = receivedAppointments; }

  
    @Override
    public String toString() {
        return "User [userId=" + userId + ", name=" + name + ", mobileNo=" + mobileNo +
               ", email=" + email + ", role=" + role +
               ", category=" + (category != null ? category.getCategoryName() : "None") + "]";
    }

   
    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(userId, other.userId);
    }
}
