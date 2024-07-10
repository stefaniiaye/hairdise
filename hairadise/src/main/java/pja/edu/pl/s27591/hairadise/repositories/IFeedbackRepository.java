package pja.edu.pl.s27591.hairadise.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s27591.hairadise.entities.Feedback;

public interface IFeedbackRepository extends CrudRepository<Feedback, Integer> {
}
