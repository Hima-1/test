package com.himawan.gymstis.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeminjamanUserRequest {
    @NotNull(message = "Tanggal tidak boleh kosong")
    LocalDate date;
}
