package com.lms.login_signup_jwt.repo;



//import com.lms.login_signup_jwt.auth.domain.AppUser;
import com.lms.login_signup_jwt.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);
    Optional<AppUser> findByEmail(String email);  // Method to find users by email
}
