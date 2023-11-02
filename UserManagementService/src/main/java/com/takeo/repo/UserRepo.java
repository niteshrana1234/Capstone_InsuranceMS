package com.takeo.repo;

import com.takeo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {

    public Optional<UserEntity> findByEmail(String email);
    public Optional<UserEntity> findByOtp(String otp);

}
