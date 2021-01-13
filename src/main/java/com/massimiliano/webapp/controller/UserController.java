package com.massimiliano.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.service.UserService;

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
    @GetMapping("/users")
    private Iterable<Users> getListaUtenti() {
        // metodo findAll di userServiceImp
        Iterable<Users> listaUtenti = userService.selezionaUtenti();
        logger.info("Visualizzazione Utenti");
        return listaUtenti;
        
    }

    // @GetMapping("/users/{id}")
    // public ResponseEntity<UserDTO> userById(@PathVariable("idUtente") int idUtente) throws NotFoundException {

    //     logger.info("Otteniamo l'utente con id " + idUtente + " *******");
	// 	Users user = userService.selezionaById(idUtente);
		
	// 	if (user == null){
	// 		String ErrMsg = String.format("L'utente con id %d non è stato trovato!", idUtente);
    //         logger.warn(ErrMsg);
    //         throw new NotFoundException(ErrMsg);
	// 	}
	// 	// return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	// }

    // creating a delete mapping that deletes a specified user
    @DeleteMapping("/users/{id}")
    private void deleteUser(@PathVariable("idUtente") int idUtente) {
        userService.Elimina(idUtente);
    }



}