package com.example.interviewtask.model.dto;


import java.time.LocalDate;

public class ExportAllDto extends FirstNameBaseDto{

    private String lastName;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String emailAddress;



    public String getLastName() {
        return lastName;
    }

    public ExportAllDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public ExportAllDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ExportAllDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ExportAllDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }


}
