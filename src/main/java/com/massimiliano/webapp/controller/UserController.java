package com.massimiliano.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.micrometer.core.ipc.http.HttpSender.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.service.UserService;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// restController
@RestController
@RequestMapping("users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

// ridare un http status alla fine di ogni metodo in modo tale da avere una risposta

    @Autowired
    private UserService userService;

    // creating a get mapping that retrieves all the users detail from the database
    private List<Users> getListaUtenti() {

        // metodo findAll di userServiceImp
        List<Users> listaUtenti = userService.selezionaUtenti();
        logger.info("Visualizzazione Utenti");
        return listaUtenti;
        
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> userById(@PathVariable("idUtente") int idUtente) {

        logger.info("Otteniamo l'utente con id " + idUtente + " *******");
		UserDTO user = userService.selezionaById(idUtente);
		
		if (user == null){
			String ErrMsg = String.format("L'utente con id %d non è stato trovato!", idUtente);
			logger.warn(ErrMsg);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

    // creating a delete mapping that deletes a specified user
    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("idUtente") int idUtente) {
        userService.Elimina(idUtente);
    }

    

}