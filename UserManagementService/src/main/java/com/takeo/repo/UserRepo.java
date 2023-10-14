package com.takeo.repo;

import com.takeo.entity.UserDetails;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserDetails,Integer> {

    public Optional<UserDetails> findByEmail(String email);
    public Optional<UserDetails> findByOtp(String otp);

}