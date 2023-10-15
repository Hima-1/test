package com.himawan.gymstis.repository;

import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.entity.Peminjaman;
import com.himawan.gymstis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "peminjaman", path = "peminjaman")
public interface PeminjamanRepository extends PagingAndSortingRepository<Peminjaman, Long>, CrudRepository<Peminjaman,Long>, JpaRepository<Peminjaman, Long> {
    boolean existsByUserAndJadwal(User user, Jadwal jadwal);
    Optional<Peminjaman> findById(Long id);
}

