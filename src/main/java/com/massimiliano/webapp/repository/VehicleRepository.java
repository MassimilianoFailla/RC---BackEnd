package com.massimiliano.webapp.repository;

import java.util.List;

import com.massimiliano.webapp.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VehicleRepository extends JpaRepository<Vehicles, String> {
    
    Vehicles findById(int id);

    @Query(value = "Select * from mezzo Where targa like:targa", nativeQuery = true)
    Vehicles selByTargaLike(@Param("targa") String targa);

    @Query(value= "Select * from mezzo Where modello like:modello",  nativeQuery = true)
    List<Vehicles> selByModelloLike(@Param("modello") String modello);

    @Query(value= "Select * from mezzo Where casaCostruttrice like:casaCostruttrice",  nativeQuery = true)
    List<Vehicles> selByCasaCostruttriceLike(@Param("casaCostruttrice") String casaCostruttrice);

    @Query(value= "Select * from mezzo Where annoImmatricolazione like:annoImmatricolazione",  nativeQuery = true)
    List<Vehicles> selByAnnoImmatricolazioneLike(@Param("annoImmatricolazione") String annoImmatricolazione);

}