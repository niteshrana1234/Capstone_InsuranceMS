package com.takeo.repo;

import com.takeo.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepo extends JpaRepository<Policy,Integer> {
   public Optional<Policy> findByUserId(int userId);
}
