package com.massimiliano.webapp.service.implementation;

import java.util.ArrayList;
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
@Transactional(readOnly = true) // 'readOnly = true' -> notazione per tutte le query, che siano sotto
                                // transazione
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public VehicleDTO trovaById(int id) {

        // verificare se il veicolo non sia nullo
        Vehicles vehicle = vehicleRepository.findById(id);
        VehicleDTO vehicleDto = null;

        if (vehicle != null) {
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
    public List<VehicleDTO> selezionaVeicoli() {
        List<Vehicles> listVeicoli = vehicleRepository.findAll();

        // creo la lista dto
        List<VehicleDTO> listDtoVeh = new ArrayList<VehicleDTO>();

        for (int i = 0; i < listVeicoli.size(); i++) {
            Vehicles vehicle = listVeicoli.get(i);
            listDtoVeh.add(modelMapper.map(vehicle, VehicleDTO.class));
        }
        return listDtoVeh;
    }

    @Override
    @Transactional
    public void InsVehicle(Vehicles vehicle) {

        vehicleRepository.save(vehicle);

    }

    @Override
    @Transactional
    public void DelVeh(VehicleDTO veicoloDto) {

        Vehicles vehicle = modelMapper.map(veicoloDto, Vehicles.class);
        vehicleRepository.delete(vehicle);
    }

}