package pja.edu.pl.s27591.hairadise.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s27591.hairadise.entities.HairService;

import java.util.List;

public interface IServiceRepository extends CrudRepository<HairService, Integer> {
    @Query("SELECT s FROM Service s " +
            "LEFT JOIN s.appointments a " +
            "LEFT JOIN a.feedback f " +
            "GROUP BY s.serviceId " +
            "ORDER BY COALESCE(AVG(f.rating), 0) DESC, COUNT(a) DESC")
    List<HairService> findTop5ByFeedbackOrAppointments();
}
