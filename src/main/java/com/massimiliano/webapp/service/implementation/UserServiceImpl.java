package com.massimiliano.webapp.service.implementation;

import java.util.List;

import javax.transaction.Transactional;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.repository.UserRepository;
import com.massimiliano.webapp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // notazione di servizio
@Transactional() // 'readOnly = true' -> notazione per tutte le query, che siano sotto transazione
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Users> selezionaUtenti() {
        return userRepository.findAll();
    }

    @Override
    public Users selezionaById(int id) {
        Users Users = userRepository.findById(id);
        return Users;
    }

    @Override
    public List<Users> selezionaUtentiByRole(String role) {
        return userRepository.selByRoleLike(role);
    }

    @Override
    public void Salva(Users Users) {
        userRepository.save(Users);
    }

    @Override
    public void Elimina(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Users> trovaPerNome(String nome) {
        return userRepository.selByNomeLike(nome);
    }

    @Override
    public List<Users> trovaPerCognome(String cognome) {
        return userRepository.selByCognomeLike(cognome);
    }

    @Override
    public Users trovaPerEmail(String email) {
        return userRepository.selByEmailLike(email);
    }

    @Override
    public List<Users> trovaPerAnnoNascita(String annoNascita) {
        return userRepository.selByDateLike(annoNascita);
    }

    @Override
    public Users trovaPerPassword(String password) {
        return userRepository.selByPasswordLike(password);
    }

    @Override
    public Users trovaPerUsername(String username) {
        return userRepository.selByUsernameLike(username);
    }
}