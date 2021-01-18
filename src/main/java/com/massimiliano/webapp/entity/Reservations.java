package com.massimiliano.webapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "Prenotazione")
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataInizio", columnDefinition = "TIMESTAMP")
    private Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataFine", columnDefinition = "TIMESTAMP")
    private Date dataFine;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "idUtente", referencedColumnName = "id")
    private Users utente;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "targa", referencedColumnName = "targa")
    private Vehicles veicolo;

    @Column(name = "approvazione")
    private boolean approvazione;

    public Reservations() {

    }

    public Reservations(Date inizioPrenotazione, Date finePrenotazione, Users utente, Vehicles veicolo, boolean approvazione) {
        this.dataInizio = inizioPrenotazione;
        this.dataFine = finePrenotazione;
        this.utente = utente;
        this.veicolo = veicolo;
        this.approvazione = approvazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInizioPrenotazione(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public void setFinePrenotazione(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getInizioPrenotazione() {
        return dataInizio;
    }

    public Date getFinePrenotazione() {
        return dataFine;
    }

    public Users getUtente() {
        return utente;
    }

    public void setUtente(Users utente) {
        this.utente = utente;
    }

    public Vehicles getVehicle() {
        return veicolo;
    }

    public void setVehicle(Vehicles veicolo) {
        this.veicolo = veicolo;
    }

    public boolean getApprovazione() {
        return approvazione;
    }

    public void setApprovazione(boolean approvazione) {
         this.approvazione = approvazione;
    }

    public String toString() {
        return "Prenotazione -> Data Inizio: " + dataInizio + " - Data Fine: " + dataFine
                + "\nVeicolo Prenotato: " + veicolo + "\n Da ->  " + utente +
                 "\n Approvata ->  " + approvazione + " ;";
    }
}