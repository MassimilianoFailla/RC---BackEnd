package com.massimiliano.webapp.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class ReservationDTO {

    private int id;
    private Date dataInizio, dataFine;
    private boolean approvazione;

    // possono servire per far vedere direttamente tutto l'oggetto utente o veicolo
    private UserDTO utente;
    private VehicleDTO veicolo;
}