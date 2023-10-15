package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeminjamanResponse {
    LocalDate date;
    Status status;
}
