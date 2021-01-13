package com.massimiliano.webapp.service.implementation;

import java.util.List;
import com.massimiliano.webapp.entity.Reservations;
import com.massimiliano.webapp.repository.ReservationRepository;
import com.massimiliano.webapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void Salva(Reservations prenotazione) {
        reservationRepository.save(prenotazione);
    }

    @Override
    public void Elimina(Reservations prenotazione) {
            reservationRepository.delete(prenotazione);
    }

    @Override
    public Reservations trovaReservationsPerId(int id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservations> trovaPrenotazioniPerIdUtente(int idUtente) {
        return reservationRepository.selByIdUserLike(idUtente);
    }

    @Override
    public List<Reservations> selezionaPrenotazioni() {
        return reservationRepository.findAll();
    }
    
}
