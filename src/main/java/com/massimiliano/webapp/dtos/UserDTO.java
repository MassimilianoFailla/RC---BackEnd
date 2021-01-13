package com.massimiliano.webapp.dtos;
import lombok.Data;

@Data
public class UserDTO {
    
    private String id,
    nome, 
    cognome, 
    dataNascita, 
    codiceFiscale, 
    email, 
    username, 
    password, 
    role;

}
