package com.himawan.gymstis.service;

import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.dto.UserDTO;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.enums.Gender;

public interface UserService {
    UserDTO createUser(UserDTO request);
    User getUserLogged();
    ProfileResponse changeProfile(String name, String email, Gender gender);
    ProfileResponse changePassword(String password);

    ProfileResponse deleteUser(Long id);

    void deleteCurrentUser();

    ProfileResponse changePassword(Long id, String password);

    ProfileResponse changeProfile(Long id, String name, String email, Gender gender);
}

