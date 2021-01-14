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
    private Iterable<Vehicles> getListaVeicoli() {
        // metodo findAll di userServiceImp
        Iterable<Vehicles> vehicleList = vehicleService.trovaMezzi();
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

    // trovare un utente per role
    @GetMapping(value = "/vehicle-targa/{targa}", produces = "application/json")
    public ResponseEntity<VehicleDTO> getVehicleByTarga(@PathVariable("targa") String targa) throws NotFoundException {

        VehicleDTO vehicle = vehicleService.trovaPerTarga(targa);
        return new ResponseEntity<VehicleDTO>(vehicle, new HttpHeaders(), HttpStatus.OK);
    }

    // inserimento
    @PostMapping(value = "/inserisci")
    public ResponseEntity<?> createVeh(@Valid @RequestBody Vehicles vehicle, BindingResult bindingResult)
            throws BindingException, DuplicateException {
        logger.info("Salvo il veicolo con id " + vehicle.getId());

        // controllo validità dati articolo
        // if (bindingResult.hasErrors()){
        // // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        // Disabilitare se si vuole gestire anche la modifica
        VehicleDTO checkArt = vehicleService.trovaById(vehicle.getId());

        if (checkArt != null) {
            String MsgErr = String.format("Veicolo con id -> %d presente! " + "Impossibile utilizzare il metodo POST",
                    vehicle.getId());
            logger.warn(MsgErr);
            throw new DuplicateException(MsgErr);
        }

        vehicleService.InsVehicle(vehicle);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message",
                String.format("Inserimento nuovo veicolo con id %d eseguito con successo", vehicle.getId()));

        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.CREATED);
    }

    // modifica
    @RequestMapping(value = "/modifica", method = RequestMethod.PUT)
    public ResponseEntity<InfoMsg> updateVeh(@Valid @RequestBody Vehicles vehicle, BindingResult bindingResult)
            throws BindingException, NotFoundException {
        logger.info("Modifico il veicolo con id " + vehicle.getId());

        // if (bindingResult.hasErrors()) {
        // String MsgErr = errMessage.getMessage(bindingResult.getFieldError(),
        // LocaleContextHolder.getLocale());
        // logger.warn(MsgErr);
        // throw new BindingException(MsgErr);
        // }

        VehicleDTO checkArt = vehicleService.trovaById(vehicle.getId());

        if (checkArt == null) {
            String MsgErr = String.format(
                    "Il veicolo con id %d non è presente! " + "Impossibile utilizzare il metodo PUT", vehicle.getId());
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        vehicleService.InsVehicle(vehicle);
        String code = HttpStatus.OK.toString();
        String message = String.format("Modifica veicolo con id %d Eseguita Con Successo", vehicle.getId());
        return new ResponseEntity<InfoMsg>(new InfoMsg(code, message), HttpStatus.CREATED);
    }

    // eliminazione
    @RequestMapping(value = "/elimina/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> deleteVeh(@PathVariable("id") int id) throws NotFoundException {
        logger.info("Elimino il veicolo con id " + id);
        Vehicles vehicle = vehicleService.trovaById2(id);

        if (vehicle == null) {
            String MsgErr = String.format("Il veicolo con id %d non presente! ", id);
            logger.warn(MsgErr);
            throw new NotFoundException(MsgErr);
        }

        vehicleService.DelVehicle(vehicle);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode responseNode = mapper.createObjectNode();
        responseNode.put("code", HttpStatus.OK.toString());
        responseNode.put("message", "Eliminazione veicolo con id -> " + id + " Eseguita Con Successo!");
        return new ResponseEntity<>(responseNode, new HttpHeaders(), HttpStatus.OK);

    }

}