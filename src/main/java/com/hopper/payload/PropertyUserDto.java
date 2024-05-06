package com.hopper.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PropertyUserDto {
    private long id;
    @NotEmpty
    @Size(min = 4 ,max = 20,message = "first name should be greater than 4 or less then 20")
    private String firstName;
    private String lastName;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty(message = "mobile can't be empty")
    @Size(min = 10,max = 10 ,message = "mobile num should have 10 digit")
    private String mobile;
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$",message = "Username must be alphanumeric and between 3-16 characters")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
