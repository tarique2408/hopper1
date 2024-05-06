package com.hopper.service;

import com.hopper.payload.LoginDto;
import com.hopper.payload.PropertyUserDto;

public interface PropertyUserService {
    public PropertyUserDto createUser(PropertyUserDto propertyUserDto);

    String verifyLogin(LoginDto loginDto);
}
