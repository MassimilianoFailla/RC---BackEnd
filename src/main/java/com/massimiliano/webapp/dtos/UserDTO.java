package com.massimiliano.webapp.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {

    private int id;
    private String nome, cognome;
    private Date dataNascita;
    private String codiceFiscale, email, username, password, role;
}
