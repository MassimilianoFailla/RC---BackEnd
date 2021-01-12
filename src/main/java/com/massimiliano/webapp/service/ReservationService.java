package com.massimiliano.webapp.service;

import java.util.List;

import com.massimiliano.webapp.entity.Reservations;

import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

    public void Salva(Reservations prenotazione);

    public void Aggiorna(Reservations prenotazione);

    public void Elimina(Reservations prenotazione);

    public Reservations trovaReservationsPerId(int id);

    public List<Reservations> trovaPrenotazioniPerIdUtente(int idUtente);

    public List<Reservations> selezionaPrenotazioni();
}