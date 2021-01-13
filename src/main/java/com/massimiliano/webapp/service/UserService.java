package com.massimiliano.webapp.service;

import java.util.List;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // inserire le operazioni da eseguire

    public void Salva(Users utente);

    public void Elimina(int id);

    public Iterable<Users> selezionaUtenti();

    public UserDTO selezionaById(int id);

    public List<UserDTO> selezionaUtentiByRole(String role);

    public List<UserDTO> trovaPerNome(String nome);

    public List<UserDTO> trovaPerCognome(String cognome);

    public UserDTO trovaPerEmail(String email);

    public  List<UserDTO> trovaPerAnnoNascita(String annoNascita);

    public UserDTO trovaPerUsername(String username);

    public UserDTO trovaPerPassword(String password);

	public void InsUser(Users user);

}
