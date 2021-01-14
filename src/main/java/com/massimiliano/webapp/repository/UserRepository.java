package com.massimiliano.webapp.repository;

import java.util.List;

import com.massimiliano.webapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, String> {

    Users findById(int id);

    Users deleteById(int id);

    @Query(value = "Select * from utente Where role like:role", nativeQuery = true)
    List<Users> selByRoleLike(@Param("role") String role);

    @Query(value = "Select * from utente Where nome like:nome", nativeQuery = true)
    List<Users> selByNomeLike(@Param("nome") String nome);

    @Query(value = "Select * from utente Where cognome like:cognome", nativeQuery = true)
    List<Users> selByCognomeLike(@Param("cognome") String cognome);

    @Query(value= "Select * from utente Where dataNascita like:dataNascita",  nativeQuery = true)
    List<Users> selByDateLike(@Param("dataNascita") String dataNascita);

    @Query(value= "Select * from utente Where email like:email",  nativeQuery = true)
    Users selByEmailLike(@Param("email") String email);

    @Query(value= "Select * from utente Where username like:username",  nativeQuery = true)
    Users selByUsernameLike(@Param("username") String username);

    @Query(value= "Select * from utente Where password like:password",  nativeQuery = true)
    Users selByPasswordLike(@Param("password") String password);

}