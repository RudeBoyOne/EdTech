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

import com.edtech_api.api.assembler.HabilidadeTecnicaAssembler;
import com.edtech_api.api.dtos.inputs.HabilidadeTecnicaInput;
import com.edtech_api.api.dtos.outputs.HabilidadeTecnicaOutput;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.service.HabilidadeTecnicaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/habilidadesTecnicas")
public class HabilidadeTecnicaController {

	private final HabilidadeTecnicaAssembler habilidadeTecnicaAssembler;
	
	private final HabilidadeTecnicaService habilidadeTecnicaService;
	
	private final Logger logger = LoggerFactory.getLogger(HabilidadeTecnicaController.class);
	
	@PostMapping
	public ResponseEntity<HabilidadeTecnicaOutput> criar(@Valid @RequestBody HabilidadeTecnicaInput habilidadeTecnica){
		HabilidadeTecnica habilidadeToEntity = habilidadeTecnicaAssembler.toEntity(habilidadeTecnica);
		HabilidadeTecnica habilidadeSalvaEntity = habilidadeTecnicaService.criarHabilidadeTecnica(habilidadeToEntity);
		HabilidadeTecnicaOutput habilidadeToOutput = habilidadeTecnicaAssembler.toOutput(habilidadeSalvaEntity);
		logger.info("POST HABILIDADE TECNICA - Realizado com sucesso!");
		return ResponseEntity.status(201).body(habilidadeToOutput);
	}
	
	@PutMapping("/{idHabilidade}")
	public ResponseEntity<HabilidadeTecnicaOutput> atualizar(@PathVariable Long idHabilidade, @Valid @RequestBody HabilidadeTecnicaInput habilidadeTecnica){
		if(!habilidadeTecnicaService.existeHabilidadeTecnicaById(idHabilidade)) {
			logger.error("PUT HABILIDADE TECNICA- atualizar habilidade - ERRO habilidade com id: " + idHabilidade + " não encontrado");
			return ResponseEntity.notFound().build();
		}
		HabilidadeTecnica habilidadeToEntity = habilidadeTecnicaAssembler.toEntity(habilidadeTecnica);
		habilidadeToEntity.setIdHabilidadeTecnica(idHabilidade);
		HabilidadeTecnicaOutput habilidadeToOutput = habilidadeTecnicaAssembler.toOutput(habilidadeTecnicaService.criarHabilidadeTecnica(habilidadeToEntity));
		logger.info("PUT HABILIDADE TECNICA - habilidade de id "+ idHabilidade + " - Alterado com sucesso!");
		return ResponseEntity.ok(habilidadeToOutput);
	}
	
	@GetMapping
	public List<HabilidadeTecnicaOutput> listar(){
		List<HabilidadeTecnica> habilidadeTecnicasEtity = habilidadeTecnicaService.listarTodasHabilidadesTecnicas();
		List<HabilidadeTecnicaOutput> habilidadeTecnicaOutputs = habilidadeTecnicaAssembler.toCollectionOutput(habilidadeTecnicasEtity);
		logger.info("GET HABILIDADE - listar todas habilidades - Realizado com sucesso!");
 		return habilidadeTecnicaOutputs;
	}
	
	@GetMapping("/{idHabilidade}")
	public ResponseEntity<HabilidadeTecnicaOutput> buscarById(@PathVariable Long idHabilidade){
		HabilidadeTecnica habilidadeBuscada = habilidadeTecnicaService.buscarHabilidadeTecnicaById(idHabilidade);
		HabilidadeTecnicaOutput habilidadeTecnicaOutput = habilidadeTecnicaAssembler.toOutput(habilidadeBuscada);
		logger.info("GET HABILIDADE - buscar de habilidade by id: "+ idHabilidade + " - Realizado com sucesso!");
		return ResponseEntity.ok(habilidadeTecnicaOutput);
	}
	
	@DeleteMapping("/{idHabilidade}")
	public ResponseEntity<Void> deletarById(@PathVariable Long idHabilidade){
		habilidadeTecnicaService.deletarHabilidadeTecnicaById(idHabilidade);
		logger.info("DELETE HABILIDADE - deleção de habilidade by id: " + idHabilidade + " - Realizado com sucesso!");
		return ResponseEntity.noContent().build();
	}
 	
}
