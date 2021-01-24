package com.massimiliano.webapp.controller;

import com.massimiliano.webapp.Application;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = Application.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class InsertReservationTest {	 
    // private MockMvc mockMvc;
	
	// @Autowired
	// private WebApplicationContext wac;
	
	// @Autowired
	// ReservationRepository reservationRepository;
	
	// @BeforeEach
	// public void setup() throws JSONException, IOException
	// {
	// 	mockMvc = MockMvcBuilders
	// 			.webAppContextSetup(wac)
	// 			.build();	
	// }
	
	// String JsonData =  
	// 		"{\r\n" + 
	// 		"    \"id\": \"10\",\r\n" + 
	// 		"    \"dataInizio\": \"dataInizio\",\r\n" + 
	// 		"    \"dataFine\": \"dataFine\",\r\n" + 
	// 		"    }\r\n" + 
	// 		"}";
	
	// @Test
	// @Order(1)
	// public void A_testInsVehicles() throws Exception
	// {
	// 	mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations/inserisci")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(JsonData)
	// 			.accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isCreated())
	// 			.andExpect(jsonPath("$.code").value("200 OK"))
	// 			.andExpect(jsonPath("$.message").value("Inserimento prenotazione di prova Eseguita Con Successo"))
	// 			.andDo(print());

	// 			assertThat(reservationRepository.findById(10))
	// 			.extracting(Reservations::getId)
	// 			.isEqualTo(10);
	// }
	
	// @Test
	// @Order(2)
	// public void B_testErrInsVehicle() throws Exception {
	// 	mockMvc.perform(MockMvcRequestBuilders.post("/api/reservations/inserisci")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(JsonData)
	// 			.accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isNotAcceptable())
	// 			.andExpect(jsonPath("$.codice").value(406))
	// 			.andExpect(jsonPath("$.messaggio").value("Prenotazione presente! Impossibile utilizzare il metodo POST"))
	// 			.andDo(print());
	// }
	
	// String JsonDataMod =  
    // "{\r\n" + 
    // "    \"id\": \"10\",\r\n" + 
    // "    \"dataInizio\": \"dataInizio\",\r\n" + 
    // "    \"dataFine\": \"\",\r\n" +         // << data fine nulla
    // "    }\r\n" + 
    // "}";
            
	// @Test
	// @Order(3)
	// public void D_testUpdVehicles() throws Exception {
				
	// 	mockMvc.perform(MockMvcRequestBuilders.put("/api/reservations/modifica")
	// 			.contentType(MediaType.APPLICATION_JSON)
	// 			.content(JsonDataMod)
	// 			.accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isCreated())
	// 			.andExpect(jsonPath("$.code").value("200 OK"))
	// 			.andExpect(jsonPath("$.message").value("Modifica prenotazione Eseguita Con Successo"))
	// 			.andDo(print());
		
	// 	assertThat(reservationRepository.findById(2))
	// 	.extracting(Reservations::getInizioPrenotazione)
	// 	.isEqualTo(2);
		
	// }
	
	// @Test
	// @Order(4)
	// public void E_testDelVehicle() throws Exception{
	// 	mockMvc.perform(MockMvcRequestBuilders.delete("/api/reservations/elimina/1")
	// 			.accept(MediaType.APPLICATION_JSON))
	// 			.andExpect(status().isOk())
	// 			.andExpect(jsonPath("$.code").value("200 OK"))
	// 			.andExpect(jsonPath("$.message").value("Eliminazione prenotazione con id 1 Eseguita Con Successo"))
	// 			.andDo(print());
	// }
}