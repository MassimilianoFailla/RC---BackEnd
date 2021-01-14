package com.massimiliano.webapp.service.implementation;

import java.util.List;

import com.massimiliano.webapp.dtos.ReservationDTO;
import com.massimiliano.webapp.entity.Reservations;
import com.massimiliano.webapp.repository.ReservationRepository;
import com.massimiliano.webapp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // notazione di servizio
@Transactional(readOnly = true) // 'readOnly = true' -> notazione per tutte le query, che siano sotto transazione   
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void Salva(Reservations prenotazione) {
        reservationRepository.save(prenotazione);
    }

    @Override
    public void Elimina(Reservations prenotazione) {
            reservationRepository.delete(prenotazione);
    }

    @Override
    public ReservationDTO trovaReservationsPerId(int id) {

         // verificare se l'utente non sia nullo
         Reservations reservation = reservationRepository.findById(id);
         ReservationDTO reservationDto = null;
 
         if(reservation != null){
            reservationDto = modelMapper.map(reservation, ReservationDTO.class);
         }
         return reservationDto;
     }

    @Override
    public Reservations trovaReservationsPerId2(int id) {

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
    
    @Override
	@Transactional
	public void DelReservation(Reservations prenotazione) {
		reservationRepository.delete(prenotazione);
	}

    @Override
	@Transactional
	public void InsReservation(Reservations prenotazione){

		reservationRepository.save(prenotazione);
	}
}
