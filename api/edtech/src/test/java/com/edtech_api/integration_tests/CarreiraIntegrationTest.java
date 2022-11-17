package com.edtech_api.integration_tests;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edtech_api.api.dtos.inputs.CarreiraInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CarreiraIntegrationTest {
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	public void criarCarreiraTest() throws Exception {
		CarreiraInput carreiraInput = new CarreiraInput("Javaiando", 16, 4);
		
		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(carreiraInput);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/carreiras")
				.contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
	}
	
	@Test
	public void atualizarCarreiraTest() throws Exception {
		CarreiraInput carreiraInput = new CarreiraInput("Pythando", 18, 5);
		
		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(carreiraInput);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/carreiras/{idCarreira}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payloadJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}
	
	@Test
	public void buscarTodasCarreirasTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/carreiras")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void buscarCarreiraByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/carreiras/{idCarreira}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());		
	}
	
	@Test
	public void deletarCarreiraByIdTest() throws Exception {
		this.criarCarreiraTest();
		mockMvc.perform(MockMvcRequestBuilders.delete("/carreiras/{idCarreira}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
