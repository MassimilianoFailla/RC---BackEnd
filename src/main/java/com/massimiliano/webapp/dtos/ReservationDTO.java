package com.massimiliano.webapp.dtos;

import java.util.Date;

import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.entity.Vehicles;

import lombok.Data;

@Data
public class ReservationDTO {
    
    private int id;
    private Date dataInizio, dataFine;
    private Users users = new Users();
    private Vehicles vehicles = new Vehicles();
    private boolean approvazione;

}
