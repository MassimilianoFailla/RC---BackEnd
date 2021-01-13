package com.massimiliano.webapp.dtos;


import lombok.Data;

@Data
public class UserDTO {
    
    private String nome, cognome, dataNascita, codiceFiscale, email, username, password, role;

}
