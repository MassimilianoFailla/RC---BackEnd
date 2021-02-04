package com.massimiliano.webapp.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.massimiliano.webapp.dtos.InfoMsg;
import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.exception.BindingException;
import com.massimiliano.webapp.exception.DuplicateException;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.UserService;
import com.massimiliano.webapp.service.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// restController
@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
//localhost:4200
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUserDetailsService jwtService;

    // @Autowired
    // private ResourceBundleMessageSource errMessage;

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    public Iterable<UserDTO> getListaUtenti() {

        // metodo findAll di userServiceImp
        Iterable<UserDTO> listaUtenti = userService.selezionaUtenti();
        logger.info("Visualizzazione Utenti");
        return listaUtenti;

    }

    // trovare un utente per username
    @GetMapping(value = "/user/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable("username") String username) throws NotFoundException {

        UserDTO userDto = userService.trovaPerUsername(username);
        return new ResponseEntity<UserDTO>(userDto, new HttpHeaders(), HttpStatus.OK);

    }

    // trovare un utente per id
    @GetMapping(value = "/user-id/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> geUserById(@PathVariable("id") int id) throws NotFoundException {

        logger.info("Visualizzazione utente con id -> %d", +id);

        UserDTO user = userService.selezionaById(id);

        return new ResponseEntity<UserDTO>(user, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un utente per role
    @GetMapping(value = "/user-role/{role}", produces = "application/json")
    public ResponseEntity<List<UserDTO>> geUserByRole(@PathVariable("role") String role) throws NotFoundException {

        List<UserDTO> userList = userService.selezionaUtentiByRole(role);

        return new ResponseEntity<List<UserDTO>>(userList, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un utente per nome
    @GetMapping(value = "/username/{username}", produces = "application/json")
    public ResponseEntity<UserDTO> geUserByUserame(@PathVariable("username") String username) throws NotFoundException {

        logger.info("****** Otteniamo l'utente con nome " + username + " *******");
        UserDTO userDTO = userService.trovaPerUsername(username.toUpperCase() + "%");

        if (userDTO == null) {
            String errMsg = String.format("Non è stato trovato alcun utente con questo username -> %s", username);
            logger.warn(errMsg);
            throw new NotFoundException(errMsg);
        } else
            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createArt(@Valid @RequestBody Users user, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        logger.info("Salvo l'utente con id " + user.getId());

        UserDTO userDTO = userService.selezionaById(user.getId());

        if (userDTO != null) {
            String MsgErr = String.format("Utente con id -> " + user.getId() + " presente! - Impossibile inserire!",
                    user.getId());
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        logger.info("hai salvato l'utente\n\n\n\n\n\n\n");

        // sistemare questo insert user in modo tale che sia un dto
        userService.InsUser(user);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento nuovo utente con id -> " + user.getId() + "eseguito con successo"));
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);

    }

    // modifica
    @RequestMapping(value = "/modifica/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<InfoMsg> updateUsr(@Valid @RequestBody Users user, @PathVariable("id") int id, BindingResult bindingResult)
            throws BindingException, NotFoundException {

        logger.info("Modifico l'utente con id " + user.getId());

        UserDTO userDTO = userService.selezionaById(user.getId());

        if (userDTO == null) {
            String MsgErr = String.format(
                    "L'utente con id -> " + user.getId() + " non è presente! " + "Impossibile modificare");
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        userService.InsUser(user);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica utente con id -> " + user.getId() + " eseguita Con Successo");
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteUSrById(@PathVariable("id") int id) throws NotFoundException {


        logger.info("Eliminazione utente con id -> " + id + "\n");
        System.out.println(id);
        UserDTO userDTO = userService.selezionaById(id);
        if (userDTO == null) {
            String MsgErr = String.format("L'utente con id -> " + id + "non presente! ");
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }
        userService.DelUser(userDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Utente " + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }
}
