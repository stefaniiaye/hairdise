package pja.edu.pl.s27591.hairadise.entities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;
    private LocalDateTime aDate;
    @ManyToOne
    private Client client;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Feedback feedback;

    @ManyToOne
    private Hairdresser hairdresser;

    @ManyToOne
    private HairService service;

    public Appointment() {
    }

    public boolean isWithin48Hours() {
        LocalDateTime now = LocalDateTime.now();
        return Duration.between(now, aDate).toHours() < 48;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getaDate() {
        return aDate;
    }

    public void setaDate(LocalDateTime aDate) {
        this.aDate = aDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Hairdresser getHairdresser() {
        return hairdresser;
    }

    public void setHairdresser(Hairdresser hairdresser) {
        this.hairdresser = hairdresser;
    }

    public HairService getService() {
        return service;
    }

    public void setService(HairService service) {
        this.service = service;
    }
}
