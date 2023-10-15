package com.himawan.gymstis.dto;

import com.himawan.gymstis.enums.Gender;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private List<String> roles;
}
