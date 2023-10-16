package com.himawan.gymstis.service.implementation;

import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.dto.UserDTO;
import com.himawan.gymstis.entity.Role;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.enums.Gender;
import com.himawan.gymstis.enums.RoleEnum;
import com.himawan.gymstis.mapper.UserMapper;
import com.himawan.gymstis.repository.RoleRepository;
import com.himawan.gymstis.repository.UserRepository;
import com.himawan.gymstis.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Error: Email Sudah Terdaftar. Tidak Bisa Mendaftar Dengan Email Sama!");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(RoleEnum.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        User user = UserMapper.mapToUser(userDto);
        user.setRoles(roles);
        return UserMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    public User getUserLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.findByEmail(currentPrincipalName);
    }

    @Override
    public ProfileResponse changeProfile(String name, String email, Gender gender) {
        User user = getUserLogged();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        userRepository.save(user);
        return UserMapper.toProfileResponse(user);
    }

    @Override
    public ProfileResponse changePassword(String password) {
        User user = getUserLogged();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return UserMapper.toProfileResponse(user);
    }

    @Override
    public ProfileResponse deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Error: User " + id + " tidak ditemukan");
        }
        User user = optionalUser.get();
        userRepository.deleteById(id);
        return UserMapper.toProfileResponse(user);
    }

    @Override
    public void deleteCurrentUser() {
        User user = getUserLogged();
        userRepository.delete(user);
    }

    @Override
    public ProfileResponse changePassword(Long id, String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Error: User " + id + " tidak ditemukan");
        }
        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return UserMapper.toProfileResponse(user);
    }

    @Override
    public ProfileResponse changeProfile(Long id, String name, String email, Gender gender) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Error: User " + id + " tidak ditemukan");
        }
        User user = optionalUser.get();
        user.setName(name);
        user.setEmail(email);
        user.setGender(gender);
        userRepository.save(user);
        return UserMapper.toProfileResponse(user);
    }
}

