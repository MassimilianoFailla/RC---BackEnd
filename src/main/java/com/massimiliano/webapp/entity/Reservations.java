package com.massimiliano.webapp.entity;

import java.io.Serializable;
import javax.persistence.*;
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

    @Column(name = "inizioPrenotazione")
    private String inizioPrenotazione;

    @Column(name = "finePrenotazione")
    private String finePrenotazione;

    @ManyToOne
    @JoinColumn(name = "idUtente", referencedColumnName = "id")
    private Users utente;

    @ManyToOne
    @JoinColumn(name = "targa", referencedColumnName = "id")
    private Vehicles veicolo;

    public Reservations() {

    }

    public Reservations(String inizioPrenotazione, String finePrenotazione, Users utente, Vehicles veicolo) {
        this.inizioPrenotazione = inizioPrenotazione;
        this.finePrenotazione = finePrenotazione;
        this.utente = utente;
        this.veicolo = veicolo;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setInizioPrenotazione(String inizioPrenotazione) {

        this.inizioPrenotazione = inizioPrenotazione;
    }

    public void setFinePrenotazione(String finePrenotazione) {

        this.finePrenotazione = finePrenotazione;
    }

    public String getInizioPrenotazione() {

        return inizioPrenotazione;
    }

    public String getFinePrenotazione() {

        return finePrenotazione;
    }

    public Users getUtente() {
        return utente;
    }

    public void setUtente(Users utente) {
        this.utente = utente;
    }

    public Vehicles getMezzo() {
        return veicolo;
    }

    public void setMezzo(Vehicles veicolo) {
        this.veicolo = veicolo;
    }

    public String toString() {
        return "Prenotazione -> Data Inizio: " + inizioPrenotazione + " - Data Fine: " + finePrenotazione
                + "\nVeicolo Prenotato: " + veicolo + "\n Da ->  " + utente + " ;";
    }
}