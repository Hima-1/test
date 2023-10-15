package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JadwalDTO {
    LocalDate date;
    Gender gender;
    Integer kuota = 10;
}
