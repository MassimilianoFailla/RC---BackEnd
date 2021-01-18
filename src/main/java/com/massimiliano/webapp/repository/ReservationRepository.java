package com.massimiliano.webapp.repository;

import java.util.List;

import com.massimiliano.webapp.entity.Reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservations, String>{
    
    Reservations findById(int id);

    @Query(value = "SELECT a FROM Prenotazione a WHERE a.utente.id like:idUtente", nativeQuery = true)
    List<Reservations> selByIdUserLike(@Param("idUtente") int idUtente);

    @Query(value = "SELECT a FROM Prenotazione a WHERE a.veicolo.targa like:targa", nativeQuery = true)
    List<Reservations> selByTargaVehicleLike(@Param("targa") String targa);

}
