package com.massimiliano.webapp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import com.massimiliano.webapp.Application;
import com.massimiliano.webapp.entity.Vehicles;
import com.massimiliano.webapp.repository.VehicleRepository;
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
public class InsertVehicleTest{
	 
    private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	VehicleRepository vehicleRepository;
	
	@BeforeEach
	public void setup() throws JSONException, IOException
	{
		mockMvc = MockMvcBuilders
				.webAppContextSetup(wac)
				.build();	
	}
	
	String JsonData =  
			"{\r\n" + 
			"    \"id\": \"5\",\r\n" + 
			"    \"casaCostruttrice\": \"BMW\",\r\n" + 
			"    \"annoImmatricolazione\": \"2020-12-17\",\r\n" + 
			"    \"modello\": \"M1\",\r\n" + 
			"    \"targa\": POL886JK,\r\n" + 
			"    }\r\n" + 
			"}";
	
	@Test
	@Order(1)
	public void A_testInsVehicles() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Inserimento veicolo di prova Eseguita Con Successo"))
				.andDo(print());

				assertThat(vehicleRepository.findById(10))
				.extracting(Vehicles::getId)
				.isEqualTo(10);
	}
	
	@Test
	@Order(2)
	public void B_testErrInsVehicle() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicles/inserisci")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonData)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotAcceptable())
				.andExpect(jsonPath("$.codice").value(406))
				.andExpect(jsonPath("$.messaggio").value("Veicolo presente! Impossibile utilizzare il metodo POST"))
				.andDo(print());
	}
	
	String JsonDataMod =  
	"{\r\n" + 
	"    \"id\": \"5\",\r\n" + 
	"    \"casaCostruttrice\": \"BMW\",\r\n" + 
	"    \"annoImmatricolazione\": \"2020-12-17\",\r\n" + 
	"    \"modello\": \"M3\",\r\n" + 		// << modello cambiato
	"    \"targa\": POL886JK,\r\n" + 
	"    }\r\n" + 
	"}";
            
	@Test
	@Order(3)
	public void D_testUpdVehicles() throws Exception {
				
		mockMvc.perform(MockMvcRequestBuilders.put("/api/vehicles/modifica")
				.contentType(MediaType.APPLICATION_JSON)
				.content(JsonDataMod)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Modifica veicolo Eseguita Con Successo"))
				.andDo(print());
		
		assertThat(vehicleRepository.findById(2))
		.extracting(Vehicles::getModello)
		.isEqualTo(2);
		
	}
	
	@Test
	@Order(4)
	public void E_testDelVehicle() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/vehicles/elimina/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("200 OK"))
				.andExpect(jsonPath("$.message").value("Eliminazione veicolo con id 1 Eseguita Con Successo"))
				.andDo(print());
	}
}