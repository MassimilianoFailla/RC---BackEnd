package com.massimiliano.webapp.service;

import java.util.List;
import com.massimiliano.webapp.dtos.VehicleDTO;
import com.massimiliano.webapp.entity.Vehicles;

import org.springframework.stereotype.Service;

@Service
public interface VehicleService {

   public Iterable<Vehicles> trovaMezzi();

   public void Salva(Vehicles veicolo);

   public void Elimina(Vehicles veicolo);

   public void InsVehicle(Vehicles veicolo);

	public void DelVehicle(Vehicles veicolo);

   public VehicleDTO trovaById(int id);

   public Vehicles trovaById2(int id);

   public VehicleDTO trovaPerTarga(String targa);

   public List<Vehicles> trovaPerModello(String modello);

   public List<Vehicles> trovaPerCasaCostruttrice(String casaCostruttrice);

   public List<Vehicles> trovaPerAnnoImmatricolazione(String annoImmatricolazione);

}