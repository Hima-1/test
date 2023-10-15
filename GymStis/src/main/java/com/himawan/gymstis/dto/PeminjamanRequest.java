package com.himawan.gymstis.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeminjamanRequest {
    LocalDate date;
}
