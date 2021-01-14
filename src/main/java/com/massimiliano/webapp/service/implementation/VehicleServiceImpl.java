package com.massimiliano.webapp.service.implementation;

import java.util.List;

import com.massimiliano.webapp.dtos.VehicleDTO;
import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.repository.VehicleRepository;
import com.massimiliano.webapp.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service // notazione di servizio
@Transactional(readOnly = true) // 'readOnly = true' -> notazione per tutte le query, che siano sotto transazione   
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public void Salva(Vehicles veicolo) {
        vehicleRepository.save(veicolo);
    }

    @Override
    public void Elimina(Vehicles veicolo) {
        vehicleRepository.delete(veicolo);
    }

    @Override
    public VehicleDTO trovaById(int id) {

         // verificare se l'utente non sia nullo
         Vehicles vehicle = vehicleRepository.findById(id);
         VehicleDTO vehicleDto = null;
 
         if(vehicle != null){
            vehicleDto = modelMapper.map(vehicle, VehicleDTO.class);
         }
         return vehicleDto;
     }

    @Override
    public Vehicles trovaById2(int id) {

        return vehicleRepository.findById(id);    
    }

    @Override
    public VehicleDTO trovaPerTarga(String targa) {

        Vehicles vehicle = vehicleRepository.selByTargaLike(targa);
        VehicleDTO vehicleDto = modelMapper.map(vehicle, VehicleDTO.class);

        return vehicleDto;    
    }

    @Override
    public List<Vehicles> trovaMezzi() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicles> trovaPerModello(String modello) {
        return vehicleRepository.selByModelloLike(modello);
    }

    @Override
    public List<Vehicles> trovaPerCasaCostruttrice(String casaCostruttrice) {
        return vehicleRepository.selByCasaCostruttriceLike(casaCostruttrice);
    }

    @Override
    public List<Vehicles> trovaPerAnnoImmatricolazione(String annoImmatricolazione) {
        return vehicleRepository.selByAnnoImmatricolazioneLike(annoImmatricolazione);
    }
 
    @Override
	@Transactional
	public void DelVehicle(Vehicles veicolo) {
		vehicleRepository.delete(veicolo);
	}

    @Override
	@Transactional
	public void InsVehicle(Vehicles veicolo){

		vehicleRepository.save(veicolo);
	}

}