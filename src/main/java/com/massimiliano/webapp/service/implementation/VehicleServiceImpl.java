package com.massimiliano.webapp.service.implementation;

import java.util.List;

import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.repository.VehicleRepository;
import com.massimiliano.webapp.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;

public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public void Salva(Vehicles veicolo) {
        vehicleRepository.save(veicolo);
    }

    @Override
    public void Elimina(Vehicles veicolo) {
        vehicleRepository.delete(veicolo);
    }

    @Override
    public Vehicles trovaById(int id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicles trovaPerTarga(String targa) {
        return vehicleRepository.selByTargaLike(targa);
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
}