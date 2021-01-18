package com.massimiliano.webapp.service.implementation;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.repository.UserRepository;
import com.massimiliano.webapp.service.UserService;
import org.modelmapper.ModelMapper;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

@Service // notazione di servizio
@Transactional(readOnly = true) // 'readOnly = true' -> notazione per tutte le query, che siano sotto transazione   
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
    public UserDTO selezionaById(int id) {

        // verificare se l'utente non sia nullo
        Users user = userRepository.findById(id);
        UserDTO userDto = null;

        if(user != null){
            userDto = modelMapper.map(user, UserDTO.class);
        }
        return userDto;
    }

    @Override
    public Users selezionaById2(int id) {

        return userRepository.findById(id);
    }

    @Override
    public List<UserDTO> selezionaUtentiByRole(String role) {

        List<Users> usersList = userRepository.selByRoleLike(role);
        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public List<UserDTO> trovaPerNome(String nome) {

        List<Users> usersList = userRepository.selByNomeLike(nome);
        List<UserDTO> retVal = usersList.stream().map(source -> modelMapper.map(source, UserDTO.class))
                .collect(Collectors.toList());

        return retVal;
    }

    @Override
    public List<UserDTO> trovaPerCognome(String cognome) {

        List<Users> usersList = userRepository.selByCognomeLike(cognome);
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
    public List<UserDTO> trovaPerAnnoNascita(Date annoNascita) {

        List<Users> usersList = userRepository.selByDateLike(annoNascita);
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

    @Override
	@Transactional
	public void DelUser(Users user) {
		userRepository.delete(user);
	}

    @Override
	@Transactional
	public void InsUser(Users user){

        userRepository.save(user);
        
	}
}