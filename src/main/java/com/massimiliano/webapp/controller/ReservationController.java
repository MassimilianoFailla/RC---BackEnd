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
import com.massimiliano.webapp.dtos.ReservationDTO;
import com.massimiliano.webapp.entity.Reservations;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.ReservationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {

private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una risposta

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
    
}