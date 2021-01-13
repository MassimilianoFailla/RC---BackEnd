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
import com.massimiliano.webapp.dtos.VehicleDTO;
import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.VehicleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// restController
@RestController
@RequestMapping("api/vehicles")
public class VehicleController {

    private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);

    // ridare un http status alla fine di ogni metodo in modo tale da avere una
    // risposta

    @Autowired
    private VehicleService vehicleService;

    @ModelAttribute("Vehicle")
    public Vehicles getVehicle() {
        return new Vehicles();
    }

    // creating a get mapping that retrieves all the users detail from the database
    @GetMapping("/views")
    private Iterable<Vehicles> getListaVeicoli() {
        // metodo findAll di userServiceImp
        Iterable<Vehicles> vehicleList = vehicleService.trovaMezzi();
        logger.info("Visualizzazione Veicoli");
        return vehicleList;

    }

    // trovare un veicolo per id
    @GetMapping("/vehicle-id/{id}")
    public ResponseEntity<VehicleDTO> geVehicleById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione veicolo con id -> %d", +id);
        VehicleDTO vehicle = vehicleService.trovaById(id);
        return new ResponseEntity<VehicleDTO>(vehicle, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un utente per role
    @GetMapping("/vehicle-targa/{targa}")
    public ResponseEntity<VehicleDTO> getVehicleByTarga(@PathVariable("targa") String targa) throws NotFoundException {

        VehicleDTO vehicle = vehicleService.trovaPerTarga(targa);
        return new ResponseEntity<VehicleDTO>(vehicle, new HttpHeaders(), HttpStatus.OK);
    }
}
