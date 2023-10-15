package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JadwalResponse {
    String hari;
    LocalDate date;
    Gender gender;
    Integer peminjam;
    Integer kuota;
}
