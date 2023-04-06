package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.ApplicationUser;
import com.manning.sbip.ch06.repository.UserRepository;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApplicationUser createUser(UserDto userDto) {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setFirstName(userDto.getFirstName());
            applicationUser.setLastName(userDto.getLastName());
            applicationUser.setEmail(userDto.getEmail());
            applicationUser.setUsername(userDto.getUsername());
            applicationUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(applicationUser);
    }

    @Override
    public ApplicationUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
