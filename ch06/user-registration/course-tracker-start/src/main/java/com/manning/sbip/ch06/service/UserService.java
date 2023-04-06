package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.ApplicationUser;
import dto.UserDto;

public interface UserService {
    ApplicationUser createUser(UserDto userDto);

    ApplicationUser findByUsername(String username);
}
