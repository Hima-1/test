package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileEditRequest {
    private String name;
    private String email;
    private Gender gender;
}
