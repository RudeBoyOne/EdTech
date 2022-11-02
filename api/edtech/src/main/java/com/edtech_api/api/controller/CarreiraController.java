package com.edtech_api.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edtech_api.api.assembler.CarreiraAssembler;
import com.edtech_api.api.dtos.inputs.CarreiraInput;
import com.edtech_api.api.dtos.outputs.CarreiraOutput;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.service.CarreiraService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/carreiras")
public class CarreiraController {

	private final CarreiraService carreiraService;
	
	private final CarreiraAssembler carreiraAssembler;
	
	private final Logger logger = LoggerFactory.getLogger(CarreiraController.class);
	
	@PostMapping
	public ResponseEntity<CarreiraOutput> criar(@Valid @RequestBody CarreiraInput carreira){
		Carreira carreiraToEntity = carreiraAssembler.toEntity(carreira);
		Carreira carreiraSalvaEntity = carreiraService.criarCarreira(carreiraToEntity);
		CarreiraOutput carreiraToOutput = carreiraAssembler.toOutput(carreiraSalvaEntity);
		logger.info("POST CARREIRA - Realizado com sucesso!");
		return ResponseEntity.status(201).body(carreiraToOutput);
	}
	
	@PutMapping("{idCarreira}")
	public ResponseEntity<CarreiraOutput> atualizar(@PathVariable Long idCarreira, @Valid @RequestBody CarreiraInput carreira){
		if(!carreiraService.existeCarreiraById(idCarreira)) {
			logger.error("PUT CARREIRA - atualizar carreira - ERRO carreira com o id: "+ idCarreira + " não encontrado");
			return ResponseEntity.notFound().build();
		}
		Carreira carreiraParaSerSalva = carreiraAssembler.toEntity(carreira);
		carreiraParaSerSalva.setIdCarreira(idCarreira);
		CarreiraOutput carreiraOutput = carreiraAssembler.toOutput(carreiraService.criarCarreira(carreiraParaSerSalva));
		logger.info("PUT CARREIRA - carreira de id: "+ idCarreira + " - Alterado com sucesso!");
		return ResponseEntity.ok(carreiraOutput);
	}
	
	@GetMapping
	public List<CarreiraOutput> listar(){
		List<Carreira> carreirasBuscadas = carreiraService.listarCarreiras();
		List<CarreiraOutput> carreiraOutputs = carreiraAssembler.toCollectionOutput(carreirasBuscadas);
		logger.info("GET CARREIRAS - listar todas carreiras - Realizado com sucesso!");
		return carreiraOutputs;
	}
	
	@GetMapping("/{idCarreira}")
	public ResponseEntity<CarreiraOutput> buscarById(@PathVariable Long idCarreira){
		Carreira carreiraBuscada = carreiraService.buscarCarreiraById(idCarreira);
		CarreiraOutput carreiraOutput = carreiraAssembler.toOutput(carreiraBuscada);
		logger.info("GET CARREIRAS - buscar de carreria by id: "+ idCarreira +  " - Realizado com sucesso!");
		return ResponseEntity.ok(carreiraOutput);
	}
	
	@DeleteMapping("{idCarreira}")
	public ResponseEntity<Void> deletarById(@PathVariable Long idCarreira){
		carreiraService.deletarCarreiraById(idCarreira);
		logger.info("DELETE CARREIRAS - deleção de carreira by id: "+ idCarreira + " - Realizado com sucesso!");
		return ResponseEntity.noContent().build();
	}
	
}
