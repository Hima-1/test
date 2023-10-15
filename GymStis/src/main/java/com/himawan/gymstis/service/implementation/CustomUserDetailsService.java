package com.himawan.gymstis.service.implementation;

import com.himawan.gymstis.dto.UserDTO;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO loadUserByUsername(String email) throws
            UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " +
                    email);
        }
        return UserDTO.build(user);
    }
}
