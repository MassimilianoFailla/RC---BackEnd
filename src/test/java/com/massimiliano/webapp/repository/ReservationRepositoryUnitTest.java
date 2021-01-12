package com.massimiliano.webapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.massimiliano.webapp.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest()
@ContextConfiguration(classes = Application.class)
public class ReservationRepositoryUnitTest {
 
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void TestfindByIdLike(){
        assertThat(reservationRepository.findById(2));
    }

    @Test
    public void TestfindByIdUserLike(){
        assertThat(reservationRepository.selByIdUserLike(1));
    }

}
