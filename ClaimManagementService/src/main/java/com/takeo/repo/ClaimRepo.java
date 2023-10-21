package com.takeo.repo;

import com.takeo.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRepo extends JpaRepository<Claim,Integer> {
    public Optional<Claim> findByUserId(int userId);
    public Optional<Claim> findByPolicyId(int policyId);
}
