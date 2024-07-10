package pja.edu.pl.s27591.hairadise.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.s27591.hairadise.entities.Client;

import java.util.Optional;


public interface IClientRepository extends CrudRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
}
