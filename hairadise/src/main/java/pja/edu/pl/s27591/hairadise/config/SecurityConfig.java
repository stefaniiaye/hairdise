package pja.edu.pl.s27591.hairadise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/", "/hairdressers", "/services", "/top5", "/styles/**",
                        "/images/**", "/register","/my-console/**").permitAll()
                .anyRequest().authenticated());
        http.formLogin(login -> login.loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/services", true)
                .permitAll());
        http.csrf(csft -> csft.disable());
        
        return http.build();
    }
}
