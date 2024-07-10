package pja.edu.pl.s27591.hairadise.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingConfirmationDTO {
    private LocalDateTime aDate;
    private String hairdresser;
    private String service;
    private int duration;
    private int deposit;
    private int price;

    public LocalDateTime getaDate() {
        return aDate;
    }

    public void setaDate(LocalDateTime aDate) {
        this.aDate = aDate;
    }

    public String getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(String hairdresser) {
        this.hairdresser = hairdresser;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
