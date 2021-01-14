package com.massimiliano.webapp.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 5, max = 20, message = "{Size.Users.nome.Validation}")
	@NotNull(message = "{NotNull.Users.nome.Validation}")
    private String nome;

    @Column(name = "cognome")
    @Size(min = 5, max = 20, message = "{Size.Users.cognome.Validation}")
	@NotNull(message = "{NotNull.Users.cognome.Validation}")
    private String cognome;

    @Column(name = "dataNascita")
	@NotNull(message = "{NotNull.Users.dataNascita.Validation}")
    private String dataNascita;

    @Column(name = "codiceFiscale")
    @Size(min = 16, max = 16, message = "{Size.Users.codiceFiscale.Validation}")
	@NotNull(message = "{NotNull.Users.codiceFiscale.Validation}")
    private String codiceFiscale;

    @Column(name = "email")
    @Size(min = 5, max = 20, message = "{Size.Users.email.Validation}")
	@NotNull(message = "{NotNull.Users.email.Validation}")
    private String email;

    @Column(name = "username")
    @Size(min = 5, max = 20, message = "{Size.Users.username.Validation}")
	@NotNull(message = "{NotNull.Users.username.Validation}")
    private String username;

    @Column(name = "password")
    @Size(min = 5, max = 20, message = "{Size.Users.password.Validation}")
	@NotNull(message = "{NotNull.Users.password.Validation}")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "utente")
    private List<Reservations> listaPrenotazioni;

    public List<Reservations> getPrenotazione() {
        return listaPrenotazioni;
    }

    public Users() {

    }

    public Users(String nome, String cognome, String dataNascita, String codiceFiscale, String email,
                  String username, String password, String role) {
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

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
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
        return "Utente -> Id: "+id+" - Nome: " + nome + " - Cognome: " + cognome + " - Data Nascita: " + dataNascita + " - Codice Fiscale: "
                + codiceFiscale + " - Email: " + email + " - Username: " + username
                + " - Password: " + password + " - Tipologia Account: " + role + ";";
    }

}