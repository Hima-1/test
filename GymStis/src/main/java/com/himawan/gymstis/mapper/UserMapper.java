package com.himawan.gymstis.mapper;

import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.dto.UserDTO;
import com.himawan.gymstis.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public static User mapToUser(UserDTO userDto){
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .build();
    }
    public static UserDTO mapToUserDto(User user){
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }

    public static ProfileResponse MaptoUserDto(User user) {
        List<String> roleNames = user.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toList());

        return ProfileResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .gender(user.getGender())
                .roles(roleNames)
                .build();
    }

    public static User mapToUser(ProfileResponse profileResponse) {
        return User.builder()
                .name(profileResponse.getName())
                .email(profileResponse.getEmail())
                .password(profileResponse.getPassword())
                .gender(profileResponse.getGender())
                .build();
    }
}

