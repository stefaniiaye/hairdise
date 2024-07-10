package pja.edu.pl.s27591.hairadise.services;

import org.springframework.stereotype.Service;
import pja.edu.pl.s27591.hairadise.DTOs.BookingConfirmationDTO;
import pja.edu.pl.s27591.hairadise.DTOs.BookingRequestDTO;
import pja.edu.pl.s27591.hairadise.entities.Appointment;
import pja.edu.pl.s27591.hairadise.entities.Client;
import pja.edu.pl.s27591.hairadise.entities.HairService;
import pja.edu.pl.s27591.hairadise.entities.Hairdresser;
import pja.edu.pl.s27591.hairadise.repositories.IAppointmentRepository;
import pja.edu.pl.s27591.hairadise.repositories.IClientRepository;
import pja.edu.pl.s27591.hairadise.repositories.IHairdresserRepository;
import pja.edu.pl.s27591.hairadise.repositories.IServiceRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainPageService {
    private final IServiceRepository serviceRepo;
    private final IHairdresserRepository hairdresserRepo;
    private final IClientRepository clientRepo;
    private final IAppointmentRepository appointmentRepo;

    public MainPageService(IServiceRepository serviceRepo, IHairdresserRepository hairdresserRepo, IClientRepository clientRepo, IAppointmentRepository appointmentRepo) {
        this.serviceRepo = serviceRepo;
        this.hairdresserRepo = hairdresserRepo;
        this.clientRepo = clientRepo;
        this.appointmentRepo = appointmentRepo;
    }

    public List<HairService> getAllServices(){
        return (List<HairService>) serviceRepo.findAll();
    }

    public List<Hairdresser> getAllHairdressers(){
        return (List<Hairdresser>) hairdresserRepo.findAll();
    }

    public List<Hairdresser> getTop5Hairdressers() {
        return hairdresserRepo.findTop5ByAppointments().stream().limit(5).collect(Collectors.toList());
    }

    public List<HairService> getTop5Services() {
        return serviceRepo.findTop5ByFeedbackOrAppointments().stream().limit(5).collect(Collectors.toList());
    }

    public List<Hairdresser> getAvailableHairdressers(LocalDateTime start, int serviceId) {
        int duration = serviceRepo.findById(serviceId).get().getDuration();
        LocalDateTime end = start.plusMinutes(duration);
        List<Hairdresser> hairdressers = (List<Hairdresser>) hairdresserRepo.findAll();
        List<Hairdresser> availableHairdressers = new ArrayList<>();

        for (Hairdresser hairdresser : hairdressers) {
            boolean isAvailable = true;

            for (Appointment appointment : hairdresser.getAppointments()) {
                LocalDateTime appointmentStart = appointment.getaDate();
                LocalDateTime appointmentEnd = appointmentStart.plusMinutes(appointment.getService().getDuration());

                if (start.isBefore(appointmentEnd) && end.isAfter(appointmentStart)) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                availableHairdressers.add(hairdresser);
            }
        }

        return availableHairdressers;
    }

    public void bookAnAppointment(BookingRequestDTO dto){
        Appointment appointment = new Appointment();
        appointment.setClient(clientRepo.findById(dto.getClientId()).get());
        appointment.setService(serviceRepo.findById(dto.getServiceId()).get());
        appointment.setHairdresser(hairdresserRepo.findById(dto.getHairdresserId()).get());
        appointment.setaDate(dto.getDate());

        appointmentRepo.save(appointment);
    }

    public BookingConfirmationDTO appointmentReview(BookingRequestDTO dto){
        BookingConfirmationDTO confirmation = new BookingConfirmationDTO();
        HairService service = serviceRepo.findById(dto.getServiceId()).get();
        Hairdresser hairdresser = hairdresserRepo.findById(dto.getHairdresserId()).get();
        Client client = clientRepo.findById(dto.getClientId()).get();

        confirmation.setaDate(dto.getDate());
        confirmation.setService(service.getName());
        confirmation.setHairdresser(hairdresser.getName()+" "+hairdresser.getSurname());
        confirmation.setDuration(service.getDuration());

        if(getTop5Services().contains(service)){
            confirmation.setPrice(service.getPrice() - (int)(service.getPrice()*0.05));
            confirmation.setDeposit(confirmation.getPrice()/10);
        }else if(client.getAppointments().isEmpty()){
            confirmation.setPrice(service.getPrice() - (int)(service.getPrice()*0.2));
            confirmation.setDeposit(confirmation.getPrice()/10);
        }else{
            confirmation.setPrice(service.getPrice());
            confirmation.setDeposit(confirmation.getPrice()/10);
        }
        return confirmation;
    }


}
