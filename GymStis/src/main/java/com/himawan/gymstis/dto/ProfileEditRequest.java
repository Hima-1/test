package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEditRequest {
    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;

    @Email(message = "Email harus valid")
    private String email;

    @NotNull(message = "Gender tidak boleh kosong")
    private Gender gender;
}
