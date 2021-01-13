package com.massimiliano.webapp.controller;

import javax.validation.Valid;
import com.massimiliano.webapp.dtos.InfoMsg;
import com.massimiliano.webapp.dtos.ReservationDTO;
import com.massimiliano.webapp.entity.Reservations;
import com.massimiliano.webapp.exception.BindingException;
import com.massimiliano.webapp.exception.DuplicateException;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.ReservationService;
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
public class ReservationController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una
    // risposta
    @Autowired
    private ReservationService reservationService;

    @ModelAttribute("Reservation")
    public Reservations getReservation() {
        return new Reservations();
    }

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    private Iterable<Reservations> getListaReservations() {
        // metodo findAll di userServiceImp
        Iterable<Reservations> reservationsList = reservationService.selezionaPrenotazioni();
        logger.info("Visualizzazione Prenotazioni");
        return reservationsList;

    }

    // trovare un utente per id
    @GetMapping("/reservation-id/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione prenotazione con id -> %d", +id);
        ReservationDTO reservation = reservationService.trovaReservationsPerId(id);
        return new ResponseEntity<ReservationDTO>(reservation, new HttpHeaders(), HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci-reservations")
    public ResponseEntity<?> createRes(@Valid @RequestBody Reservations reservation, BindingResult bindingResult)
            throws BindingException, DuplicateException {
        logger.info("Salvo la prenotazione con id " + reservation.getId());

        // controllo validità dati articolo
        // if (bindingResult.hasErrors()){
        // // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        // Disabilitare se si vuole gestire anche la modifica
        ReservationDTO checkArt = reservationService.trovaReservationsPerId(reservation.getId());

        if (checkArt != null) {
            String MsgErr = String.format(
                    "Prenotazione con idx %d presente! " + "Impossibile utilizzare il metodo POST",
                    reservation.getId());
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        reservationService.InsReservation(reservation);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();

        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", String.format("Inserimento Utente %s eseguito con successo", reservation.getId()));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }

    // modifica
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<InfoMsg> updateRes(@Valid @RequestBody Reservations reservation, BindingResult bindingResult)
            throws BindingException, NotFoundException {
        logger.info("Modifico la prenotazione con id " + reservation.getId());

        // if (bindingResult.hasErrors()) {
        // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        ReservationDTO checkArt = reservationService.trovaReservationsPerId(reservation.getId());

        if (checkArt == null) {
            String MsgErr = String.format(
                    "La prenotazione con id %d non è presente! " + "Impossibile utilizzare il metodo PUT",
                    reservation.getId());
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        reservationService.InsReservation(reservation);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica prenotazione con id %d Eseguita Con Successo", reservation.getId());
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteRes(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Elimino la prenotazione con id " + id);
        Reservations reservation = reservationService.trovaReservationsPerId2(id);

        if (reservation == null) {
            String MsgErr = String.format("La prenotazione con %d non presente! ", id);
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        reservationService.DelReservation(reservation);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione prenotazione con id -> " + id + " Eseguita Con Successo!");
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

}