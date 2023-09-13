package com.example.interviewtask.model.dto;

import jakarta.validation.constraints.Email;

public class UpdateUserByFirstNameDto extends FirstNameBaseDto{

    @Email(message = "Invalid email address")
    private String email;


    public String getEmail() {
        return email;
    }

    public UpdateUserByFirstNameDto setEmail(String email) {
        this.email = email;
        return this;
    }


}
