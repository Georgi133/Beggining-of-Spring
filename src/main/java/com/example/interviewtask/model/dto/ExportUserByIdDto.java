package com.example.interviewtask.model.dto;


import java.time.LocalDate;

public class ExportUserByIdDto {
    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    private String emailAddress;

    public String getFirstName() {
        return firstName;
    }

    public ExportUserByIdDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ExportUserByIdDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public ExportUserByIdDto setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ExportUserByIdDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ExportUserByIdDto setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
        return this;
    }

    @Override
    public String toString() {
        String date = getDateOfBirth() == null ? "None" : getDateOfBirth().toString();
        return "name: " + getFirstName() + " " + getLastName() + "\n" +
                "email :" + getEmailAddress() + "\nphone: " + getPhoneNumber() + "\n" +
                "date: " + date;
    }


}
