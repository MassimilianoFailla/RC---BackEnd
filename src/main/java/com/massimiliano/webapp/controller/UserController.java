package com.massimiliano.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// restController
@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una risposta

    @Autowired
    private UserService userService;

    @ModelAttribute("Utente")
    public Users getUtente() {
        return new Users();
    }

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    private Iterable<Users> getListaUtenti() {
        // metodo findAll di userServiceImp
        Iterable<Users> listaUtenti = userService.selezionaUtenti();
        logger.info("Visualizzazione Utenti");
        return listaUtenti;
        
    }

    // trovare un utente per id
    @GetMapping("/user-id/{id}")
    public ResponseEntity<UserDTO> geUserById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione utente con id -> %d", +id);
        UserDTO user = userService.selezionaById(id);
        return new ResponseEntity<UserDTO>(user, new HttpHeaders(), HttpStatus.OK);
    }
    
    // trovare un utente per role
    @GetMapping("/user-role/{role}")
    public ResponseEntity<List<UserDTO>> geUserByRole(@PathVariable("role") String role) throws NotFoundException {

        List<UserDTO> userList = userService.selezionaUtentiByRole(role);
 
        return new ResponseEntity<List<UserDTO>>(userList, new HttpHeaders(), HttpStatus.OK);
    }

     // trovare un utente per nome
     @GetMapping(value = "/user-name/{nome}", produces = "application/json")
     public ResponseEntity<List<UserDTO>> geUserByName(@PathVariable("nome") String nome) throws NotFoundException {
 
        logger.info("****** Otteniamo l'utente con nome " + nome + " *******");
        
         List<UserDTO> userList = userService.trovaPerNome(nome.toUpperCase() +"%");
  
         if(userList == null){
             String errMsg = String.format("Non è stato trovato alcun utente con questo nome -> %s", nome);
             logger.warn(errMsg);
             throw new NotFoundException(errMsg);
        }
        else 
         return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
     }
}