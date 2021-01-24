package com.massimiliano.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Data
@Entity
@Table(name = "veicolo")
public class Vehicles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "casaCostruttrice")
    private String casaCostruttrice;

    @Column(name = "modello")
    private String modello;

    @Temporal(TemporalType.DATE)
    @Column(name = "annoImmatricolazione", columnDefinition = "TIMESTAMP")
    private Date annoImmatricolazione;

    @Column(name = "targa", unique = true)
    public String targa;

    @Column(name = "tipologia")
    private String tipologia;

    // @JsonIgnore
    @OneToMany(mappedBy = "veicolo", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservations> listaPrenotazioni;

    public List<Reservations> getListaPrenotazioni() {
        return listaPrenotazioni;
    }

    public Vehicles() {

    }

    public Vehicles(String casaCostruttrice, String modello, Date annoImmatricolazione, String targa,
            String tipologia) {
        this.casaCostruttrice = casaCostruttrice;
        this.modello = modello;
        this.annoImmatricolazione = annoImmatricolazione;
        this.targa = targa;
        this.tipologia = tipologia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCasaCostruttrice() {
        return casaCostruttrice;
    }

    public void setCasaCostruttrice(String casaCostruttrice) {
        this.casaCostruttrice = casaCostruttrice;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Date getAnnoImmatricolazione() {
        return annoImmatricolazione;
    }

    public void setAnnoImmatricolazione(Date annoImmatricolazione) {
        this.annoImmatricolazione = annoImmatricolazione;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setListaPrenotazioni(List<Reservations> listaPrenotazioni) {
        this.listaPrenotazioni = listaPrenotazioni;
    }

    public String toString() {
        return "Veicolo -> Modello: " + modello + " - Casa Costruttrice: " + casaCostruttrice
                + " - Anno Immatricolazione: " + annoImmatricolazione + " - Targa: " + targa + " - Tipologia Veicolo: "
                + tipologia + ";";
    }
}