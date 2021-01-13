package com.massimiliano.webapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.context.support.ResourceBundleMessageSource;
// import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.massimiliano.webapp.dtos.InfoMsg;
import com.massimiliano.webapp.dtos.UserDTO;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.exception.BindingException;
import com.massimiliano.webapp.exception.DuplicateException;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// restController
@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una
    // risposta

    @Autowired
    private UserService userService;

    // @Autowired
    // private ResourceBundleMessageSource errMessage;

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    private Iterable<Users> getListaUtenti() {
        // metodo findAll di userServiceImp
        Iterable<Users> listaUtenti = userService.selezionaUtenti();
        logger.info("Visualizzazione Utenti");
        return listaUtenti;

    }

    // trovare un utente per id
    @GetMapping(value = "/user-id/{id}", produces = "application/json")
    public ResponseEntity<UserDTO> geUserById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione utente con id -> %d", +id);
        UserDTO user = userService.selezionaById(id);
        return new ResponseEntity<UserDTO>(user, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un utente per role
    @GetMapping(value = "/user-role/{role}", produces="application/json")
    public ResponseEntity<List<UserDTO>> geUserByRole(@PathVariable("role") String role) throws NotFoundException {

        List<UserDTO> userList = userService.selezionaUtentiByRole(role);

        return new ResponseEntity<List<UserDTO>>(userList, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un utente per nome
    @GetMapping(value = "/user-name/{nome}", produces = "application/json")
    public ResponseEntity<List<UserDTO>> geUserByName(@PathVariable("nome") String nome) throws NotFoundException {

        logger.info("****** Otteniamo l'utente con nome " + nome + " *******");

        List<UserDTO> userList = userService.trovaPerNome(nome.toUpperCase() + "%");

        if (userList == null) {
            String errMsg = String.format("Non è stato trovato alcun utente con questo nome -> %s", nome);
            logger.warn(errMsg);
            throw new NotFoundException(errMsg);
        } else
            return new ResponseEntity<List<UserDTO>>(userList, HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci-user")
    public ResponseEntity<?> createArt(@Valid @RequestBody Users user, BindingResult bindingResult)
            throws BindingException, DuplicateException {
        logger.info("Salvo l'utente con id " + user.getId());

        // if (bindingResult.hasErrors()){
        // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        // Disabilitare se si vuole gestire anche la modifica
        UserDTO checkArt = userService.selezionaById(user.getId());

        if (checkArt != null) {
            String MsgErr = String.format("Utente %s presente! " + "Impossibile utilizzare il metodo POST",
                    user.getId());
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        userService.InsUser(user);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Utente %s eseguito con successo", user.getId()));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }


    // modifica
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<InfoMsg> updateUsr(@Valid @RequestBody Users user, BindingResult bindingResult)
            throws BindingException, NotFoundException {
        logger.info("Modifico l'utente con id " + user.getId());

        // if (bindingResult.hasErrors()) {
        // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        UserDTO checkArt = userService.selezionaById(user.getId());

        if (checkArt == null) {
            String MsgErr = String.format(
                    "L'utente con id %d non è presente! " + "Impossibile utilizzare il metodo PUT", user.getId());
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        userService.InsUser(user);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica user con id %d Eseguita Con Successo", user.getId());
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteUSr(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Elimino l'utente con id " + id);
        Users user = userService.selezionaById2(id);

        if (user == null) {
            String MsgErr = String.format("L'utente %d non presente! ", id);
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        userService.DelUser(user);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione user con id -> " + id + " Eseguita Con Successo!");
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }

}