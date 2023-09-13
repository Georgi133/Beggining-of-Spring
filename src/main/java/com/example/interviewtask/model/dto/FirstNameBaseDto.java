package com.example.interviewtask.model.dto;

import jakarta.validation.constraints.Size;

public  class FirstNameBaseDto {

    @Size(min = 2, message = "Name should be longer or equal to 2 letter")
    protected String firstName;

    public String getFirstName() {
        return firstName;
    }

    public FirstNameBaseDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }


}
