package com.edtech_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edtech_api.domain.exceptions.EdtechException;
import com.edtech_api.domain.exceptions.RecursoNaoEncontrado;
import com.edtech_api.domain.model.Carreira;
import com.edtech_api.domain.repository.ICarreiraRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CarreiraService {

	private final ICarreiraRepository carreiraRepository;
	
	
	public Carreira criarCarreira(Carreira carreira) {
		boolean carreiraJaExistente = carreiraRepository.findByNome(carreira.getNome())
				.stream().anyMatch(carreiraExistente -> !carreiraExistente.equals(carreira));
		
		if(carreiraJaExistente) {
			throw new EdtechException("Já existe uma Carreira cadastrada com este nome, tente novamente!");
		}
		return carreiraRepository.save(carreira);
	}
	
	public List<Carreira> listarCarreiras(){
		return carreiraRepository.findAll();
	}
	
	public Carreira buscarCarreiraById(Long idCarreira) {
		return carreiraRepository.findById(idCarreira).orElseThrow(
				() -> new RecursoNaoEncontrado("Carreira não econtrada ou não existente!"));
	}
	
	public boolean existeCarreiraById(Long idCarreira) {
		return carreiraRepository.existsById(idCarreira);
	}
	
	public void deletarCarreiraById(Long idCarreira) {
		if(existeCarreiraById(idCarreira)) {
			carreiraRepository.deleteById(idCarreira);
		}else {
			throw new RecursoNaoEncontrado("Carreira não encontrada ou não existente, tente novamente!");
		}
	}
}
