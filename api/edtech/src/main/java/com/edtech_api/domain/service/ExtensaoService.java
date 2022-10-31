package com.edtech_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edtech_api.domain.exceptions.EdtechException;
import com.edtech_api.domain.exceptions.RecursoNaoEncontrado;
import com.edtech_api.domain.model.Extensao;
import com.edtech_api.domain.repository.IExtensaoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ExtensaoService {

	private final IExtensaoRepository extensaoRepository;
	
	
	public Extensao criarExtensao(Extensao extensao) {
		boolean extensaoJaExiste = extensaoRepository.findByNome(extensao.getNome())
				.stream().anyMatch((extensaoExistente) -> !extensaoExistente.equals(extensao));
		if(extensaoJaExiste) {
			throw new EdtechException("Já existe uma Extensão cadastrada com este nome, tente novamente!");
		}
		return extensaoRepository.save(extensao);
	}
	
	public List<Extensao> listarTodasExtensoes(){
		return extensaoRepository.findAll();
	}
	
 	public Extensao buscarExtensaoById(Long idExtesao) {
		return extensaoRepository.findById(idExtesao).orElseThrow(
				() -> new RecursoNaoEncontrado("Extensão não encontrado  ou não existente, tente novamente!"));
	}
 	
 	public boolean existeExtensaoById(Long idExtensao) {
 		return extensaoRepository.existsById(idExtensao);
 	}
 	
 	public void deletarExtensaoById(Long idExtensao) {
 		if(existeExtensaoById(idExtensao)) {
 			extensaoRepository.deleteById(idExtensao);
 		}else {
 			throw new RecursoNaoEncontrado("Extensao não encontrada ou não existente, tente novamente!");
 		}
 	}
}
