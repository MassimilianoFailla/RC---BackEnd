package com.massimiliano.webapp.controller;

import javax.validation.Valid;
import com.massimiliano.webapp.dtos.InfoMsg;
import com.massimiliano.webapp.dtos.VehicleDTO;
import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.exception.BindingException;
import com.massimiliano.webapp.exception.DuplicateException;
import com.massimiliano.webapp.exception.NotFoundException;
import com.massimiliano.webapp.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.context.support.ResourceBundleMessageSource;
// import org.springframework.context.support.ResourceBundleMessageSource;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

// restController
@RestController
@RequestMapping("api/vehicles")
@CrossOrigin(origins = "http://localhost:4200")
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
    private Iterable<VehicleDTO> getListaVeicoli() {

        Iterable<VehicleDTO> vehicleList = vehicleService.selezionaVeicoli();
        logger.info("Visualizzazione Veicoli");
        return vehicleList;

    }

    // trovare un veicolo per id
    @GetMapping(value = "/vehicle-id/{id}", produces = "application/json")
    public ResponseEntity<VehicleDTO> geVehicleById(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Visualizzazione veicolo con id -> %d", +id);
        VehicleDTO vehicle = vehicleService.trovaById(id);
        return new ResponseEntity<VehicleDTO>(vehicle, new HttpHeaders(), HttpStatus.OK);
    }

    // trovare un veicolo per targa
    @GetMapping(value = "/vehicle-targa/{targa}", produces = "application/json")
    public ResponseEntity<VehicleDTO> getVehicleByTarga(@PathVariable("targa") String targa) throws NotFoundException {

        VehicleDTO vehicle = vehicleService.trovaPerTarga(targa);
        return new ResponseEntity<VehicleDTO>(vehicle, new HttpHeaders(), HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createVeh(@Valid @RequestBody Vehicles vehicle, BindingResult bindingResult)
            throws BindingException, DuplicateException {

        logger.info("Salvo il veicolo con id " + vehicle.getId());

        // Disabilitare se si vuole gestire anche la modifica
        VehicleDTO vehicleDTO = vehicleService.trovaById(vehicle.getId());

        if (vehicleDTO != null) {
            String MsgErr = String.format("Veicolo con id -> " + vehicle.getId() + " presente! - Impossibile inserire!");
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        vehicleService.InsVehicle(vehicle);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message",
                String.format("Inserimento nuovo veicolo con id -> " + vehicle.getId() + " eseguito con successo"));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }


    // modifica
    @RequestMapping(value = "/modifica/{id}", method = RequestMethod.PUT)
    public ResponseEntity<InfoMsg> updateVeh(@Valid @RequestBody Vehicles vehicle, @PathVariable("id") int id, BindingResult bindingResult)
            throws BindingException, NotFoundException {

        logger.info("Modifico il veicolo con id -> " + vehicle.getId());

        VehicleDTO vehicleDTO = vehicleService.trovaById(vehicle.getId());

        if (vehicleDTO == null) {
            String MsgErr = String.format(
                    "Il veicolo con id -> " + vehicle.getId() + " non è presente! - Impossibile modificare!");
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        vehicleService.InsVehicle(vehicle);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica veicolo con id -> " + vehicle.getId() + " Eseguita Con Successo");
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteVeh(@PathVariable("id") int id) throws NotFoundException {

        logger.info("Eliminazione veicolo con id -> " + id + "\n");
        System.out.println(id);
        VehicleDTO vehicleDTO = vehicleService.trovaById(id);
        if (vehicleDTO == null) {
            String MsgErr = String.format("Il veicolo con id -> " + id + " non presente! ");
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }
        vehicleService.DelVeh(vehicleDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione Veicolo con id ->  " + id + " Eseguita Con Successo");

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);
    }
}