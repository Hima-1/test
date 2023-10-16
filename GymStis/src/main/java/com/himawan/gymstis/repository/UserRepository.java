package com.himawan.gymstis.repository;

import com.himawan.gymstis.entity.Peminjaman;
import com.himawan.gymstis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends PagingAndSortingRepository<User, Long>, CrudRepository<User,Long>, JpaRepository<User, Long> {
    User findByEmail(String username);
    boolean existsByEmail(String email);
}

