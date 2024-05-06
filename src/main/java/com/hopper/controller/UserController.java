package com.hopper.controller;

import com.hopper.payload.LoginDto;
import com.hopper.payload.PropertyUserDto;
import com.hopper.service.PropertyUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private PropertyUserService propertyUserService;

    public UserController(PropertyUserService propertyUserService) {
        this.propertyUserService = propertyUserService;
    }

    //create user / sign up
    //url-http:localhost:8080//api/v1/users/signUp
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody PropertyUserDto propertyUserDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            // If there are validation errors, construct error response
            StringBuilder errorMessage = new StringBuilder("Validation errors: ");
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessage.append(error.getField()).append(" ").append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
        PropertyUserDto user = propertyUserService.createUser(propertyUserDto);
        if(user!=null){
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    //login
    //url- http://localhost:8080/api/v1/users/login
    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
        String token = propertyUserService.verifyLogin(loginDto);
        if(token!=null){
            return new ResponseEntity<>(token,HttpStatus.OK);
        }
        return new ResponseEntity<>("username/password is wrong",HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
