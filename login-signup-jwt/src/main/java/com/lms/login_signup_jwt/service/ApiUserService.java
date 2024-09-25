package com.lms.login_signup_jwt.service;

//import com.ust.securerestapi.auth.domain.AppUser;
//import com.ust.securerestapi.auth.repo.UserRepository;
import com.lms.login_signup_jwt.exception.UserAlreadyExistsException;
import com.lms.login_signup_jwt.model.AppUser;
import com.lms.login_signup_jwt.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser dbUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(dbUser.getUsername())
                .password(dbUser.getPassword())
                .build();
    }

    public AppUser findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void saveUser(AppUser appUser) {
        if (userRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists: " + appUser.getEmail());
        }
        userRepository.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }


}

