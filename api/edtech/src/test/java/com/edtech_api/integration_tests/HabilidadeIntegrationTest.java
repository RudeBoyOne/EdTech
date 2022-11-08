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

import com.edtech_api.api.dtos.inputs.HabilidadeTecnicaInput;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class HabilidadeIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void criarHabilidadeTest() throws Exception {
		HabilidadeTecnicaInput habilidadeTecnicaInput = new HabilidadeTecnicaInput("JAVA");
		
		
		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(habilidadeTecnicaInput);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/habilidadesTecnicas")
				.contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
	}
	
	@Test
	public void updateHabilidadeTest() throws Exception {
		HabilidadeTecnicaInput habilidadeTecnicaInput = new HabilidadeTecnicaInput("Angular");
		
		ObjectWriter objectWriter = new ObjectMapper()
				.findAndRegisterModules()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(habilidadeTecnicaInput);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/habilidadesTecnicas/{idHabilidadeTecnica}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payloadJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
	}
	
	@Test
	public void searchHabilidadeByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/habilidadesTecnicas/{idHabilidadeTecnica}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());		
	}
	
	@Test
	public void buscarTodasHabilidadesTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/habilidadesTecnicas")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deletarHabilidadeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/habilidadesTecnicas/{idHabilidadeTecnica}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
		this.criarHabilidadeTest();
	}
}
