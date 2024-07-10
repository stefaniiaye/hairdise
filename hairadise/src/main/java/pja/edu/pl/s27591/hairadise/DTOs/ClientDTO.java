package pja.edu.pl.s27591.hairadise.DTOs;


import jakarta.validation.constraints.NotNull;
import pja.edu.pl.s27591.hairadise.constraint.PasswordCheck;

import java.time.LocalDate;

public class ClientDTO {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String email;
    @NotNull
    @PasswordCheck
    private String password;
    @NotNull
    private LocalDate dateOfBirth;
    private int totalNumOfPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getTotalNumOfPoints() {
        return totalNumOfPoints;
    }

    public void setTotalNumOfPoints(int totalNumOfPoints) {
        this.totalNumOfPoints = totalNumOfPoints;
    }
}
