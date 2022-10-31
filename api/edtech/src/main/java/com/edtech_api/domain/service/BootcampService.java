package com.edtech_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edtech_api.domain.exceptions.EdtechException;
import com.edtech_api.domain.exceptions.RecursoNaoEncontrado;
import com.edtech_api.domain.model.Bootcamp;
import com.edtech_api.domain.repository.IBootcampRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BootcampService {

	private final IBootcampRepository bootcampRepository;
	
	public Bootcamp criarBootcamp(Bootcamp bootcamp) {
		boolean bootcampJaExiste = bootcampRepository.findByNome(bootcamp.getNome())
				.stream().anyMatch((bootcampExistente) -> !bootcampExistente.equals(bootcamp));
		if(bootcampJaExiste) {
			throw new EdtechException("Já existe um Bootcamp cadastrado com este nome, tente novamente!");
		}
		return bootcampRepository.save(bootcamp);
	}
	
	public List<Bootcamp> listarTodosBootcamps(){
		return bootcampRepository.findAll();
	}
	
	public Bootcamp buscarBootcampById(Long idBootcamp) {
		return bootcampRepository.findById(idBootcamp).orElseThrow(
				() -> new RecursoNaoEncontrado("Bootcamp não encontrado ou inexistente!"));
	}
	
	public boolean existeBootcampById(Long idBootcamp) {
		return bootcampRepository.existsById(idBootcamp);
	}
	
	public void deletarBootcampById(Long idBootcamp) {
		if(!existeBootcampById(idBootcamp)) {
			throw new RecursoNaoEncontrado("Bootcamp não encontrado ou inexistente!");
		}
		bootcampRepository.deleteById(idBootcamp);
	}
}
