package com.dong.repository;

import com.dong.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
