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

import com.edtech_api.api.assembler.ExtensaoAssembler;
import com.edtech_api.api.dtos.inputs.ExtensaoInput;
import com.edtech_api.api.dtos.outputs.ExtensaoOutput;
import com.edtech_api.domain.model.Extensao;
import com.edtech_api.domain.service.ExtensaoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/extensoes")
public class ExtensaoController {

	private final ExtensaoService extensaoService;
	
	private final ExtensaoAssembler extensaoAssembler;
	
	private final Logger logger = LoggerFactory.getLogger(ExtensaoController.class);
	
	@PostMapping
	public ResponseEntity<ExtensaoOutput> criar(@Valid @RequestBody ExtensaoInput extensao){
		Extensao extensaoToEntity = extensaoAssembler.toEntity(extensao);
		Extensao extensaoSalvaEntity = extensaoService.criarExtensao(extensaoToEntity);
		ExtensaoOutput extensaoToOutput = extensaoAssembler.toOutput(extensaoSalvaEntity);
		logger.info("POST EXTENSAO - Realizado com sucesso!");
		return ResponseEntity.status(201).body(extensaoToOutput);
	}
	
	@PutMapping("/{idExtensao}")
	public ResponseEntity<ExtensaoOutput> atualizar(@PathVariable Long idExtensao, @Valid @RequestBody ExtensaoInput extensao){
		if(!extensaoService.existeExtensaoById(idExtensao)) {
			logger.error("PUT EXTENSAO - atualizar extensão - ERRO extesão com id: " + idExtensao + " não encontrado");
			return ResponseEntity.notFound().build();
		}
		Extensao extensaoToEntity = extensaoAssembler.toEntity(extensao);
		extensaoToEntity.setIdExtensao(idExtensao);
		ExtensaoOutput extensaoOutput = extensaoAssembler.toOutput(extensaoService.criarExtensao(extensaoToEntity));
		logger.info("PUT EXTENSAO - extensão de id "+ idExtensao + " - Alterado com sucesso!");
		return ResponseEntity.ok(extensaoOutput);
	}
	
	@GetMapping
	public List<ExtensaoOutput> listar(){
		List<Extensao> extensoesBuscadas = extensaoService.listarTodasExtensoes();
		List<ExtensaoOutput> extensoesOutputs = extensaoAssembler.toCollectionOutput(extensoesBuscadas);
		logger.info("GET EXTENSAO - listar todas extensões - Realizado com sucesso!");
		return extensoesOutputs;
	}
	
	@GetMapping("/{idExtensao}")
	public ResponseEntity<ExtensaoOutput> buscarById(@PathVariable Long idExtensao){
		Extensao extensaoBuscada = extensaoService.buscarExtensaoById(idExtensao);
		ExtensaoOutput extensaoOutput = extensaoAssembler.toOutput(extensaoBuscada);
		logger.info("GET EXTENSAO - buscar de extensão by id: "+ idExtensao + " - Realizado com sucesso!");
		return ResponseEntity.ok(extensaoOutput);
	}
	
	@DeleteMapping("/{idExtensao}")
	public ResponseEntity<Void> deletarById(@PathVariable Long idExtensao){
		extensaoService.deletarExtensaoById(idExtensao);
		logger.info("DELETE EXTENSAO - deleção de extensão by id: "+ idExtensao + " - Realizado com sucesso!");
		return ResponseEntity.noContent().build();
	}
 }
