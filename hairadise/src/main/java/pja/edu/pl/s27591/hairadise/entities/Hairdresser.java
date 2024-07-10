package pja.edu.pl.s27591.hairadise.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Hairdresser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hairdresserId;
    private String name;
    private String surname;
    private String description;
    @OneToMany(mappedBy = "hairdresser", fetch = FetchType.EAGER)
    private List<Appointment> appointments;

    public Hairdresser() {
    }

    public int getHairdresserId() {
        return hairdresserId;
    }

    public void setHairdresserId(int hairdresserId) {
        this.hairdresserId = hairdresserId;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
