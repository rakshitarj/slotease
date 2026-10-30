package com.appointment.project.appointmentbackend.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.project.appointmentbackend.entity.User;

public interface UserRepository extends JpaRepository <User,Long>{
	Optional <User> findByEmail(String email);
	
	List<User> findByRole(String role);
	 List<User> findByRoleAndCategory_CategoryId(String role, Long categoryId);
	

	    // existence checks used by register logic
	    boolean existsByEmail(String email);

	    boolean existsByMobileNo(String mobileNo);

	    // optional convenience if you need it elsewhere
	    Optional<User> findByMobileNo(String mobileNo);

}
