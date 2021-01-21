package com.massimiliano.webapp.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class VehicleDTO {

    private int id;
    private Date annoImmatricolazione;
    private String casaCostruttrice, modello, targa, tipologia;
}
