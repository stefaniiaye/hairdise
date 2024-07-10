package pja.edu.pl.s27591.hairadise.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s27591.hairadise.entities.Appointment;

public interface IAppointmentRepository extends CrudRepository<Appointment, Integer> {
}
