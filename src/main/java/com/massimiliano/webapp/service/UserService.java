package com.massimiliano.webapp.service;

import java.util.Date;
import java.util.List;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    // inserire le operazioni da eseguire

    public void Salva(Users utente);

	public void InsUser(Users user);

	public void DelUser(Users user);

    public Iterable<Users> selezionaUtenti();

    public Users selezionaById2(int id);

    public UserDTO selezionaById(int id);

    public List<UserDTO> selezionaUtentiByRole(String role);

    public List<UserDTO> trovaPerNome(String nome);

    public List<UserDTO> trovaPerCognome(String cognome);

    public UserDTO trovaPerEmail(String email);

    public  List<UserDTO> trovaPerAnnoNascita(Date annoNascita);

    public UserDTO trovaPerUsername(String username);

    public UserDTO trovaPerPassword(String password);

}
