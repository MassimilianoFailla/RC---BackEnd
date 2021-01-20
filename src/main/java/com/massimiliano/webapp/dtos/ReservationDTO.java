package com.massimiliano.webapp.dtos;

import java.util.Date;
import lombok.Data;

@Data
public class ReservationDTO {

    private int id;
    private Date dataInizio, dataFine;
    private UserDTO utente = new UserDTO();
    private VehicleDTO veicolo = new VehicleDTO();
    private boolean approvazione;

}
