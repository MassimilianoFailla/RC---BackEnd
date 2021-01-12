package com.massimiliano.webapp.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.massimiliano.webapp.Application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest()
@ContextConfiguration(classes = Application.class)
public class VehicleRepositoryUnitTest {
     
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void TestFindByIdLike(){
        assertThat(vehicleRepository.findById(1));
    }

    @Test
    public void TestfindByModelloLike(){
        assertThat(vehicleRepository.selByModelloLike(""));
    }

    @Test
    public void TestfindByTargaLike(){
        assertThat(vehicleRepository.selByTargaLike(""));
    }

    @Test
    public void TestfindByCasaCostruttriceLike(){
        assertThat(vehicleRepository.selByCasaCostruttriceLike(""));
    }

    @Test
    public void TestfindByAnnoImmatricolazioneLike(){
        assertThat(vehicleRepository.selByAnnoImmatricolazioneLike(""));
    }

}