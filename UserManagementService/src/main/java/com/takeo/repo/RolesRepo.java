package com.takeo.repo;

import com.takeo.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Integer> {
    Optional<Roles> findByRoleName(String roleName);
}
