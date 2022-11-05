package com.edtech_api.integration_tests;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.edtech_api.api.dtos.inputs.ExtensaoInput;
import com.edtech_api.domain.model.enums.StatusExtensao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ExtensaoIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void criarExtensaoTest() throws Exception {
		ExtensaoInput extensaoInput = new ExtensaoInput("Dockerizando", "Assembly", LocalDate.of(2022, 05, 04), 
				LocalDate.of(2022, 10, 14), StatusExtensao.ABERTA.toString());
		
		
		ObjectWriter objectWriter = new ObjectMapper()
				.findAndRegisterModules()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(extensaoInput);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/extensoes")
				.contentType(MediaType.APPLICATION_JSON)
                .content(payloadJson))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
	}
	
	@Test
	public void updateExtensaoTest() throws Exception {
		this.criarExtensaoTest();
		ExtensaoInput extensaoInput = new ExtensaoInput("Pytando", "Assembly", LocalDate.of(2022, 05, 04), 
				LocalDate.of(2022, 10, 14), StatusExtensao.ABERTA.toString());
		
		
		ObjectWriter objectWriter = new ObjectMapper()
				.findAndRegisterModules()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer();
		
		String payloadJson = objectWriter.writeValueAsString(extensaoInput);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/extensoes/{idExtensao}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(payloadJson))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
	}
	
	@Test
	public void searchExtensaoByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/extensoes/{idExtensao}", 2)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());		
	}
	
	@Test
	public void buscarTodasExtensoes() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/extensoes")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void deletarExtensaoById() throws Exception {
		this.criarExtensaoTest();
		mockMvc.perform(MockMvcRequestBuilders.delete("/extensoes/{idExtensao}", 1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
