package com.himawan.gymstis.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.himawan.gymstis.enums.Gender;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "jadwals")
public class Jadwal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private Gender gender;
    @Column(nullable = false)
    private Integer peminjam = 0;

    @Column(nullable = false)
    private Integer kuota = 10;

    @JsonBackReference
    @OneToMany(mappedBy = "jadwal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Peminjaman> peminjamans = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}

