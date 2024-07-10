package pja.edu.pl.s27591.hairadise.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pja.edu.pl.s27591.hairadise.entities.Client;
import pja.edu.pl.s27591.hairadise.services.ClientService;

import java.util.Collections;

@Service
public class CustomClientDetailsService implements UserDetailsService {
    private final ClientService service;

    public CustomClientDetailsService(ClientService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = service.findClientByEmail(email).orElse(null);
        if (client == null) {
            throw new UsernameNotFoundException("No clients found");
        }
        return User.builder()
                .username(client.getEmail())
                .password("{noop}" + client.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
