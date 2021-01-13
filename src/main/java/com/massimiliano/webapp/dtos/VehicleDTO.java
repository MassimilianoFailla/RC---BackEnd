package com.massimiliano.webapp.dtos;
import lombok.Data;

@Data
public class VehicleDTO {
    
    private String id,
    annoImmatricolazione, 
    casaCostruttrice,
    modello, 
    targa;
}
