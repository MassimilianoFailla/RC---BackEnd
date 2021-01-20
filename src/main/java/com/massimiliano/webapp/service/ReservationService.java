package com.massimiliano.webapp.service;

import java.util.List;

import com.massimiliano.webapp.dtos.ReservationDTO;
import com.massimiliano.webapp.entity.Reservations;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {


    public void InsRes(Reservations reservation);

	public void DelReservation(ReservationDTO reservationDTO);
    
    public ReservationDTO trovaReservationsPerId(int id);

    public Reservations trovaReservationsPerId2(int id);

    public List<Reservations> trovaPrenotazioniPerIdUtente(int idUtente);

    public List<ReservationDTO> selezionaTutti();


}