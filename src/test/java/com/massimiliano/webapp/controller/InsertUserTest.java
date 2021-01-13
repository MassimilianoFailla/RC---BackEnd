package com.massimiliano.webapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import com.massimiliano.webapp.Application;
import com.massimiliano.webapp.entity.Users;
import com.massimiliano.webapp.repository.UserRepository;

import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration(classes = Application.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class InsertUserTest{
	 
    private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	UserRepository userRepository;
	
	@BeforeEach
	public void setup() throws JSONException, IOException
	{
		mockMvc = MockMvcBuilders
				.webAppContextSetup(wac)
				.build();	
	}
	
	String JsonData =  
			"{\r\n" + 
			"    \"id\": \"10\",\r\n" + 
			"    \"nome\": \"inserisci nome\",\r\n" + 
			"    \"cognome\": \"inserisci cognome\",\r\n" + 
			"    \"daraNascita\": \"inserisci data nascita\",\r\n" + 
			"    \"codiceFiscale\": inserisci codice fiscale,\r\n" + 
			"    \"email\": inserisci email,\r\n" + 
			"    \"username\": \"inserisci username \",\r\n" + 
			"    \"password\": \"inserisci password\",\r\n" + 
			"    \"role\": inserisci role,\r\n" +  
			"    }\r\n" + 
			"}";
	
	@Test
	@Order(1)
	public void A_testInsUser() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/users/inserisci-user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Inserimento Utente di prova Eseguita Con Successo"))
				.andDo(print());

				assertThat(userRepository.findByIdLike(10))
				.extracting(Users::getId)
				.isEqualTo(10);
	}
	
	@Test
	@Order(2)
	public void B_testErrInsUsers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/users/inserisci-user")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(jsonPath("$.codice").value(406))
				.andExpect(jsonPath("$.messaggio").value("Utente immesso presente in anagrafica! Impossibile utilizzare il metodo POST"))
				.andDo(print());
	}
	
	String JsonDataMod =  
    "{\r\n" + 
    "    \"id\": \"10\",\r\n" + 
    "    \"nome\": \"inserisci nome\",\r\n" + 
    "    \"cognome\": \"inserisci cognome\",\r\n" + 
    "    \"annoNascita\": \"inserisci data nascita\",\r\n" + 
    "    \"codicefiscale\": inserisci codice fiscale,\r\n" + 
    "    \"email\": email non inserita,\r\n" +      // << valore email cambiato
    "    \"username\": \"inserisci username \",\r\n" + 
    "    \"password\": \"inserisci password\",\r\n" + 
    "    \"role\": inserisci role,\r\n" +  
    "    }\r\n" + 
    "}";
            
	@Test
	@Order(3)
	public void D_testUpdUsers() throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders.put("/api/users/modifica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonDataMod)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Modifica utente Eseguita Con Successo"))
				.andDo(print());
		
		assertThat(userRepository.findByIdLike(2))
		.extracting(Users::getEmail)
		.isEqualTo(2);
		
	}
	
	@Test
	@Order(4)
	public void E_testDelUser() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/elimina/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Eliminazione utente con id 1 Eseguita Con Successo"))
				.andDo(print());
	}
}