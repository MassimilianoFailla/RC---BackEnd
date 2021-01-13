package com.massimiliano.webapp.service.implementation;

import java.util.List;

import javax.transaction.Transactional;
import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.repository.UserRepository;
import com.massimiliano.webapp.service.UserService;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // notazione di servizio
@Transactional() // 'readOnly = true' -> notazione per tutte le query, che siano sotto transazione
                 
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Users> selezionaUtenti() {
        return userRepository.findAll();
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
    public UserDTO selezionaById(int id) {

        Users user = userRepository.findByIdLike(id);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

    @Override
    public List<UserDTO> selezionaUtentiByRole(String role) {

        List<Users> usersList = userRepository.selByRoleLike(role);

        // usersList.forEach(e -> e.getNome());
        // usersList.forEach(e -> e.setUm(e.getUm().trim()));
        // usersList.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));

        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public List<UserDTO> trovaPerNome(String nome) {

        List<Users> usersList = userRepository.selByNomeLike(nome);

        // usersList.forEach(e -> e.getNome());
        // usersList.forEach(e -> e.setUm(e.getUm().trim()));
        // usersList.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));

        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public List<UserDTO> trovaPerCognome(String cognome) {
        List<Users> usersList = userRepository.selByCognomeLike(cognome);

        // usersList.forEach(e -> e.getNome());
        // usersList.forEach(e -> e.setUm(e.getUm().trim()));
        // usersList.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));

        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public UserDTO trovaPerEmail(String email) {

        Users user = userRepository.selByEmailLike(email);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

    @Override
    public List<UserDTO> trovaPerAnnoNascita(String annoNascita) {
        List<Users> usersList = userRepository.selByDateLike(annoNascita);

        // usersList.forEach(e -> e.getNome());
        // usersList.forEach(e -> e.setUm(e.getUm().trim()));
        // usersList.forEach(e -> e.setDescrizione(e.getDescrizione().trim()));

        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public UserDTO trovaPerPassword(String password) {
        Users user = userRepository.selByPasswordLike(password);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

    @Override
    public UserDTO trovaPerUsername(String username) {
        Users user = userRepository.selByUsernameLike(username);
        UserDTO userDto = modelMapper.map(user, UserDTO.class);

        return userDto;
    }

}