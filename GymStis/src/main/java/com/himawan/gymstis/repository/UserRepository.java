package com.himawan.gymstis.repository;

import com.himawan.gymstis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
    boolean existsByEmail(String email);
}

