package com.massimiliano.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import com.massimiliano.webapp.dtos.InfoMsg;
import com.massimiliano.webapp.dtos.ReservationDTO;
import com.massimiliano.webapp.dtos.VehicleDTO;
import com.massimiliano.webapp.entity.Reservations;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.exception.BindingException;
import com.massimiliano.webapp.exception.DuplicateException;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.ReservationService;
import com.massimiliano.webapp.service.UserService;
import com.massimiliano.webapp.service.VehicleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una
    // risposta
    @Autowired
    private ReservationService reservationService;

    Users users = null;
    Vehicles vehicles = null;

    @ModelAttribute("Reservation")
    public Reservations getReservation() {
        return new Reservations();
    }

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    public ResponseEntity<List<ReservationDTO>> getListaReservations() {

        // metodo findAll di userServiceImp
        List<ReservationDTO> reservationsList = reservationService.selezionaTutti();

        logger.info("Visualizzazione Prenotazioni");

        return new ResponseEntity<List<ReservationDTO>>(reservationsList, new HttpHeaders(), HttpStatus.OK);

    }

    // trovare un utente per id
    @GetMapping(value = "/reservation-id/{id}", produces = "application/json")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione prenotazione con id -> %d", +id);
        ReservationDTO reservation = reservationService.trovaReservationsPerId(id);
        return new ResponseEntity<ReservationDTO>(reservation, new HttpHeaders(), HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createRes(@Valid @RequestBody Reservations reservation, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        logger.info("Salvo la prenotazione con id -> " + reservation.getId());

        // Disabilitare se si vuole gestire anche la modifica
        ReservationDTO reservationsDTO = reservationService.trovaReservationsPerId(reservation.getId());

        if (reservationsDTO != null) {
            String MsgErr = String.format(
                    "Prenotazione con id -> " + reservation.getId() + " presente! - Impossibile utilizzare il metodo POST",
                    reservation.getId());
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        reservationService.InsRes(reservation);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Prenotazione con id -> "+reservation.getId()+" eseguito con successo"));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }

    // modifica
    @RequestMapping(value = "/modifica/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<InfoMsg> updateRes(@Valid @RequestBody Reservations reservation,
            BindingResult bindingResult, @PathVariable("id") int id) throws BindingException, NotFoundException {
        System.out.println("Modifica utente con id -> %d" + id);

        ReservationDTO reservationDTO = reservationService.trovaReservationsPerId(reservation.getId());

        if (reservationDTO == null) {
            String MsgErr = String.format("Prenotazione non presente! " + "Impossibile utilizzare il metodo PUT");
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        reservationService.InsRes(reservation);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica prenotazione con id -> " + id + "Eseguita Con Successo");
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteResById(@PathVariable("id") int id) throws NotFoundException {

        logger.info("Eliminazione prenotazione con id -> " + id + "\n");
        System.out.println(id);
        ReservationDTO reservationDTO = reservationService.trovaReservationsPerId(id);
        if (reservationDTO == null) {
            String MsgErr = String.format("Prenotazione con %d non presente! ", +id);
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }
        reservationService.DelReservation(reservationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Prenotazione " + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }

}