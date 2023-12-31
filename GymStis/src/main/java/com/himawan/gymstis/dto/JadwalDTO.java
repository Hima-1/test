package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JadwalDTO {
    @NotNull(message = "Tanggal tidak boleh kosong")
    LocalDate date;

    @NotNull(message = "Gender tidak boleh kosong")
    Gender gender;

    @NotNull(message = "Kuota tidak boleh kosong")
    Integer kuota = 10;
}
