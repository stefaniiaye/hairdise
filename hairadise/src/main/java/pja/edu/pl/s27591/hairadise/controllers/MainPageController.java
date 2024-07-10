package pja.edu.pl.s27591.hairadise.controllers;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pja.edu.pl.s27591.hairadise.DTOs.BookingConfirmationDTO;
import pja.edu.pl.s27591.hairadise.DTOs.BookingRequestDTO;
import pja.edu.pl.s27591.hairadise.entities.Hairdresser;
import pja.edu.pl.s27591.hairadise.services.ClientService;
import pja.edu.pl.s27591.hairadise.services.MainPageService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MainPageController {
    private final MainPageService service;
    private final ClientService clientService;

    public MainPageController(MainPageService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "mainPage";
    }

    @GetMapping("/hairdressers")
    public String hairdressersList(Model model) {
        model.addAttribute("hairdressers", service.getAllHairdressers());
        return "mainPageHairdressers";
    }

    @GetMapping("/services")
    public String services(Model model) {
        model.addAttribute("services", service.getAllServices());
        return "mainPageServices";
    }

    @GetMapping("/top5")
    public String top5(Model model) {
        model.addAttribute("topHairdressers", service.getTop5Hairdressers());
        model.addAttribute("topServices", service.getTop5Services());
        return "mainPageTopFive";
    }


    @PostMapping("/book")
    public String bookAppointment(@RequestParam int serviceId, Model model) {
        model.addAttribute("serviceId", serviceId);
        return "bookingProcess";
    }

    @PostMapping("/selectDateTimeAndHairdresser")
    public String selectDateTimeAndHairdresser(@RequestParam int serviceId,
                                               @RequestParam String date,
                                               @RequestParam String time,
                                               Model model) {
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);
        model.addAttribute("serviceId", serviceId);
        model.addAttribute("dateTime", dateTime);

        List<Hairdresser> hairdressers = service.getAvailableHairdressers(dateTime, serviceId);
        model.addAttribute("hairdressers", hairdressers);

        return "bookingProcess";
    }

    /*@PostMapping("/confirmBooking")
    public String confirmBooking(@RequestParam int serviceId,
                                 @RequestParam int hairdresserId,
                                 @RequestParam String date,
                                 Model model, Authentication authentication) {
        int clientId = 0;
        if (authentication != null) {
            clientId = clientService.findClientByEmail(authentication.name()).get().getClientId();
        }
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setServiceId(serviceId);
        dto.setHairdresserId(hairdresserId);
        dto.setClientId(clientId);
        dto.setDate(LocalDateTime.parse(date));

        BookingConfirmationDTO confirmation = service.appointmentReview(dto);
        model.addAttribute("confirmation", confirmation);
        return "confirmBooking";
    }

    @PostMapping("/finalizeBooking")
    public String finalizeBooking(@RequestParam int serviceId,
                                  @RequestParam int hairdresserId,
                                  @RequestParam int clientId,
                                  @RequestParam String date) {
        BookingRequestDTO dto = new BookingRequestDTO();
        dto.setServiceId(serviceId);
        dto.setHairdresserId(hairdresserId);
        dto.setClientId(clientId);
        dto.setDate(LocalDateTime.parse(date));

        service.bookAnAppointment(dto);
        return "bookingSuccess";
    }*/

}
