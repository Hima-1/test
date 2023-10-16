package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PeminjamanStatusRequest {
    @NotNull(message = "Status tidak boleh kosong")
    Status status;
}
