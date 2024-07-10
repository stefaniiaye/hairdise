package pja.edu.pl.s27591.hairadise.controllers;

import jakarta.validation.Valid;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.s27591.hairadise.DTOs.ClientDTO;
import pja.edu.pl.s27591.hairadise.DTOs.FeedbackDTO;
import pja.edu.pl.s27591.hairadise.DTOs.LoginRequestDTO;
import pja.edu.pl.s27591.hairadise.entities.Appointment;
import pja.edu.pl.s27591.hairadise.entities.Client;
import pja.edu.pl.s27591.hairadise.services.ClientService;

import java.util.List;

@Controller
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDTO", new LoginRequestDTO());
        return "clientLogin";
    }

    @GetMapping("/successLogin")
    public String successLogin(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/account?clientId=" + service.findClientByEmail(authentication.name()).get().getClientId();
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "clientRegister";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute ClientDTO clientDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "clientRegister";
        }
        try {
            service.registerNewClient(clientDTO);
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during registration. Please try again.");
            return "clientRegister";
        }
        return "redirect:/login";
    }

    @GetMapping("/account")
    public String clientAccount(Model model, @RequestParam int clientId) {
        Client client = service.findClientById(clientId);
        model.addAttribute("client", client);
        return "clientAccount";
    }

    @GetMapping("/account/history")
    public String clientAppointments(Model model, @RequestParam int clientId) {
        List<Appointment> appointments = service.getHistoryOfAppointments(clientId);
        if (appointments == null) {
            model.addAttribute("error", "Client or appointments not found");
            return "clientHistory";
        }
        model.addAttribute("appointments", appointments);
        return "clientHistory";
    }

    @GetMapping("/account/history/feedback")
    public String feedbackForm(Model model, @RequestParam int appointmentId) {
        model.addAttribute("feedbackDTO", new FeedbackDTO());
        model.addAttribute("appointmentId", appointmentId);
        return "feedbackForm";
    }

    @PostMapping("/account/history/feedback")
    public String submitFeedback(@ModelAttribute FeedbackDTO feedbackDTO, @RequestParam int appointmentId) {
        service.submitFeedback(feedbackDTO, appointmentId);
        return "redirect:/account/history?clientId=" + service.getClientIdByAppointmentId(appointmentId);
    }

    @PostMapping("/account/history/cancel")
    public String cancelAppointment(@RequestParam int appointmentId) {
        int clientId = service.getClientIdByAppointmentId(appointmentId);
        service.cancelAppointment(appointmentId);
        return "redirect:/account/history?clientId=" + clientId;
    }
}
