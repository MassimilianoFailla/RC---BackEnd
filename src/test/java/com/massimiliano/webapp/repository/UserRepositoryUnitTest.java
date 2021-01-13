package com.massimiliano.webapp.repository;

import static org.assertj.core.api.Assertions.assertThat;
import com.massimiliano.webapp.Application;
import com.massimiliano.webapp.entity.Users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest()
@ContextConfiguration(classes = Application.class)
public class UserRepositoryUnitTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void TestfindByIdLike(){
        assertThat(userRepository.findByIdLike(1)).extracting(Users::getNome).isEqualTo("Massimiliano");        
    }

    @Test
    public void TestfindByRoleLike(){
        assertThat(userRepository.selByRoleLike(""));
    }

    @Test
    public void TestfindByNameLike(){
        assertThat(userRepository.selByNomeLike(""));
    }

    @Test
    public void TestfindByCognomeLike(){
        assertThat(userRepository.selByCognomeLike(""));
    }

    @Test
    public void TestfindByDateLike(){
        assertThat(userRepository.selByDateLike(""));
    }

    @Test
    public void TestfindByEmailLike(){
        assertThat(userRepository.selByEmailLike(""));
    }

    @Test
    public void TestfindByUsernameLike(){
        assertThat(userRepository.selByUsernameLike(""));
    }
    
}