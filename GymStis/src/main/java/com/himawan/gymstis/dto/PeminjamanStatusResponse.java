package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeminjamanStatusResponse {
    LocalDate date;
    Status status;
    Integer peminjam;
    Integer kuota;
}
