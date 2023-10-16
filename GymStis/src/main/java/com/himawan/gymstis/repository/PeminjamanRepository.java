package com.himawan.gymstis.repository;

import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.entity.Peminjaman;
import com.himawan.gymstis.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "peminjaman", path = "peminjaman")
public interface PeminjamanRepository extends PagingAndSortingRepository<Peminjaman, Long>, CrudRepository<Peminjaman,Long>, JpaRepository<Peminjaman, Long> {
    boolean existsByUserAndJadwal(User user, Jadwal jadwal);
    Optional<Peminjaman> findById(Long id);
    List<Peminjaman> findByUser(User user);
    @Operation(summary = "Find Peminjaman by date")
    List<Peminjaman> findByJadwal_Date(@Param("date")LocalDate date);
}

