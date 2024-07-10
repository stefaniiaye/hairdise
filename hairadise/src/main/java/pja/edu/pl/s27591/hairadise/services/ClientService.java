package pja.edu.pl.s27591.hairadise.services;

import org.springframework.stereotype.Service;
import pja.edu.pl.s27591.hairadise.DTOs.ClientDTO;
import pja.edu.pl.s27591.hairadise.DTOs.FeedbackDTO;
import pja.edu.pl.s27591.hairadise.entities.Appointment;
import pja.edu.pl.s27591.hairadise.entities.Client;
import pja.edu.pl.s27591.hairadise.entities.Feedback;
import pja.edu.pl.s27591.hairadise.repositories.IAppointmentRepository;
import pja.edu.pl.s27591.hairadise.repositories.IClientRepository;
import pja.edu.pl.s27591.hairadise.repositories.IFeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final IClientRepository repo;
    private final IAppointmentRepository appointmentRepo;
    private final IFeedbackRepository feedbackRepo;

    public ClientService(IClientRepository repo, IAppointmentRepository appointmentRepo, IFeedbackRepository feedbackRepo) {
        this.repo = repo;
        this.appointmentRepo = appointmentRepo;
        this.feedbackRepo = feedbackRepo;
    }

    public Client registerNewClient(ClientDTO dto){
        Client client = new Client();
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setPassword(dto.getPassword());
        client.setDateOfBirth(dto.getDateOfBirth());
        client.setTotalNumOfPoints(0);
        return repo.save(client);
    }

    public Optional<Client> findClientByEmail(String email){
       return repo.findByEmail(email);
    }
    public Client findClientById(int clientId) {
        return repo.findById(clientId).orElse(null);
    }

    public boolean credentialsAreCorrect(String email, String password) {
        Client client = findClientByEmail(email).orElse(null);
        return client != null && client.getPassword().equals(password);
    }

    public List<Appointment> getHistoryOfAppointments(int clientId){
        Client client = findClientById(clientId);
        return client.getAppointments();
    }

    public void submitFeedback(FeedbackDTO feedbackDTO, int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment != null) {
            Feedback feedback = new Feedback();
            feedback.setRating(feedbackDTO.getRating());
            feedback.setComment(feedbackDTO.getComment());
            feedback.setAppointment(appointment);
            feedbackRepo.save(feedback);

            appointment.setFeedback(feedback);
            appointmentRepo.save(appointment);
        }
    }


    public void cancelAppointment(int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        if (appointment != null) {
            Feedback feedback = appointment.getFeedback();
            if (feedback != null) {
                feedbackRepo.delete(feedback);
            }
            appointmentRepo.delete(appointment);
        }
    }

    public int getClientIdByAppointmentId(int appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId).orElse(null);
        return appointment.getClient().getClientId();
    }

    public void updateNumOfPoints(int clientId, int points){
        Client client = repo.findById(clientId).orElse(null);
        client.setTotalNumOfPoints(client.getTotalNumOfPoints()+points);
    }

}
