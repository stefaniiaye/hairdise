package pja.edu.pl.s27591.hairadise.DTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingRequestDTO {
    private LocalDateTime date;
    private int clientId;
    private int serviceId;
    private int hairdresserId;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(int hairdresserId) {
        this.hairdresserId = hairdresserId;
    }
}
