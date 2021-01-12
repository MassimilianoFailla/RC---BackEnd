package com.massimiliano.webapp.service;

import java.util.List;

import com.massimiliano.webapp.entity.Vehicles;

public interface VehicleService {

   public void Salva(Vehicles veicolo);

//    public void Aggiorna(Vehicles veicolo);

   public void Elimina(Vehicles veicolo);

   public  Vehicles trovaById(int id);

   public Vehicles trovaPerTarga(String targa);

   public List<Vehicles> trovaMezzi();

    //metodi da implementare nel filtraggio

    public List<Vehicles> trovaPerModello(String modello);

    public List<Vehicles> trovaPerCasaCostruttrice(String casaCostruttrice);

    public  List<Vehicles> trovaPerAnnoImmatricolazione(String annoImmatricolazione);

}