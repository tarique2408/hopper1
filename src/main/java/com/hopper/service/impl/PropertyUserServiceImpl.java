package com.hopper.service.impl;

import com.hopper.entity.PropertyUser;
import com.hopper.payload.LoginDto;
import com.hopper.payload.PropertyUserDto;
import com.hopper.repository.PropertyUserRepository;
import com.hopper.service.JWTService;
import com.hopper.service.PropertyUserService;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Optional;

@Service
public class PropertyUserServiceImpl implements PropertyUserService {
    private JWTService jwtService;

    private ModelMapper modelMapper;
    private PropertyUserRepository propertyUserRepository;

    public PropertyUserServiceImpl(JWTService jwtService, ModelMapper modelMapper, PropertyUserRepository propertyUserRepository) {
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
        this.propertyUserRepository = propertyUserRepository;
    }

    @Override
    public PropertyUserDto createUser(PropertyUserDto propertyUserDto) {
        //first convert the dto into entity and then save into table
        PropertyUser user=new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setEmail(propertyUserDto.getEmail());
        user.setMobile(propertyUserDto.getMobile());
        user.setUsername(propertyUserDto.getUsername());
        //first encode the password then store into table so that no one can no the actual password
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        user.setRole("user");
        PropertyUser saved = propertyUserRepository.save(user);
        PropertyUserDto dto = entityToDto(saved);
        return dto;
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {
        //first get the user details based on username
        Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(loginDto.getUsername());
        if (opUser.isPresent()){
            PropertyUser user = opUser.get();
            if(BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
               return jwtService.generateToken(user);
            }
        }

        return null;
    }

    //convert the dto into entity
    PropertyUser dtoToEntity(PropertyUserDto propertyUserDto)
    {
      return  modelMapper.map(propertyUserDto,PropertyUser.class);
    }

    //convert the entity into dto
    PropertyUserDto entityToDto(PropertyUser propertyUser){
        return modelMapper.map(propertyUser,PropertyUserDto.class);
    }
}
