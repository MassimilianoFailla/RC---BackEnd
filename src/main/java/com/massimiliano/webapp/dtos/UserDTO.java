package com.massimiliano.webapp.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String nome, cognome, codiceFiscale, email, username, password, role;
    private Date dataNascita;

}
