package com.himawan.gymstis.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.himawan.gymstis.enums.Status;
import lombok.*;
import jakarta.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "peminjamans")
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "jadwal_id", nullable = false)
    @JsonManagedReference
    private Jadwal jadwal;

    @Column(nullable = false)
    private Status status = Status.PENDING;
}

