package com.himawan.gymstis.repository;

import com.himawan.gymstis.entity.Jadwal;
import com.himawan.gymstis.enums.Gender;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "jadwal", path = "jadwal")
public interface JadwalRepository extends PagingAndSortingRepository<Jadwal, Long>, CrudRepository<Jadwal,Long>, JpaRepository<Jadwal, Long> {
    Optional<Jadwal> findByDate(LocalDate date);
    List<Jadwal> findByDateGreaterThanEqualAndGenderOrderByDateAsc(LocalDate date, Gender gender);

    @Operation(summary = "Find jadwal by gender")
    List<Jadwal> findByGender(@Param("gender") Gender gender);
}

