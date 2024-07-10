package pja.edu.pl.s27591.hairadise.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s27591.hairadise.entities.Hairdresser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IHairdresserRepository extends CrudRepository<Hairdresser,Integer> {
    @Query("SELECT h FROM Hairdresser h ORDER BY SIZE(h.appointments) DESC")
    List<Hairdresser> findTop5ByAppointments();


}
