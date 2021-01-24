package com.massimiliano.webapp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "utente")
public class Users implements Serializable {

    private static final long serialVersionUID = 291353626011036772L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cognome")
    private String cognome;

    @Temporal(TemporalType.DATE)
    @Column(name = "dataNascita", columnDefinition = "TIMESTAMP")
    private Date dataNascita;

    @Column(name = "codiceFiscale")
    private String codiceFiscale;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    // @JsonIgnore
    @OneToMany(mappedBy = "utente", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservations> listaPrenotazioni;

    public List<Reservations> getPrenotazione() {
        return listaPrenotazioni;
    }

    public Users() {

    }

    public Users(String nome, String cognome, Date dataNascita, String codiceFiscale, String email, String username,
            String password, String role) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getCognome() {

        return cognome;
    }

    public void setCognome(String cognome) {

        this.cognome = cognome;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPrenotazioneList(List<Reservations> listaPrenotazioni) {
        this.listaPrenotazioni = listaPrenotazioni;
    }

    public String toString() {
        return "Utente -> Id: " + id + " - Nome: " + nome + " - Cognome: " + cognome + " - Data Nascita: " + dataNascita
                + " - Codice Fiscale: " + codiceFiscale + " - Email: " + email + " - Username: " + username
                + " - Password: " + password + " - Tipologia Account: " + role + ";";
    }

}