package com.example.interviewtask.model.dto;

import jakarta.validation.constraints.Email;

public class ImportDto extends FirstNameBaseDto {

    private String lastName;

    private String dateOfBirth;

    private String phoneNumber;
    @Email(message = "Please insert valid email")
    private String emailAddress;



    public String getLastName() {
        return lastName;
    }

    public ImportDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ImportDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ImportDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }


}
