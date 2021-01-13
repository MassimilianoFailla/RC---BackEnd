package com.massimiliano.webapp.service;

import java.util.List;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // inserire le operazioni da eseguire
    public List<Users> selezionaUtenti();

    public UserDTO selezionaById(int id);

    public List<Users> selezionaUtentiByRole(String role);

    public void Salva(Users utente);

    public void Elimina(int id);

    // metodi da implementare per il filtraggio --------

    public List<Users> trovaPerNome(String nome);

    public List<Users> trovaPerCognome(String cognome);

    public Users trovaPerEmail(String email);

    public  List<Users> trovaPerAnnoNascita(String annoNascita);

    // -------------------------------------------------

    public Users trovaPerUsername(String username);

    public Users trovaPerPassword(String password);

}