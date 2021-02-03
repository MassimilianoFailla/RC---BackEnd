package com.massimiliano.webapp.service;

import java.util.ArrayList;
import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.repository.UserRepository;
import com.massimiliano.webapp.service.implementation.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDTO userDTO = userServiceImpl.trovaPerUsername(username);

        Users user = modelMapper.map(userDTO, Users.class);

        if (userDTO == null) {
            throw new UsernameNotFoundException("Utente non trovato con l'username : " + username);
        }

        UserBuilder builder = null;

        builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());

        String profilo = user.getRole();

        builder.authorities(profilo);

        return builder.build();

    }


    // crea un nuovo utente con password criptate
    // invece non deve creare un nuovo utente = answer?

    public Users save(UserDTO userDTO) {

        Users newUser = new Users();

        newUser.setNome(userDTO.getNome());
        newUser.setCognome(userDTO.getCognome());
        newUser.setDataNascita(userDTO.getDataNascita());
        newUser.setCodiceFiscale(userDTO.getCodiceFiscale());
        newUser.setEmail(userDTO.getEmail());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        newUser.setRole(userDTO.getRole());
//        newUser.setPrenotazioneList(userDTO.getListaPrenotazioni());

        return userRepository.save(newUser);
    }

}