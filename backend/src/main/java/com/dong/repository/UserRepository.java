package com.dong.repository;

import com.dong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    Optional<User> deleteUserByUsername(String username);
    Optional<User> deleteUserById(long id);
    Optional<User> findUserById(long id);
}
