package com.edtech_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edtech_api.domain.exceptions.EdtechException;
import com.edtech_api.domain.exceptions.RecursoNaoEncontrado;
import com.edtech_api.domain.model.HabilidadeTecnica;
import com.edtech_api.domain.repository.IHabilidadeTecnicaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HabilidadeTecnicaService {

	private final IHabilidadeTecnicaRepository habilidadeTecnicaRepository;
	
	public HabilidadeTecnica criarHabilidadeTecnica(HabilidadeTecnica habilidadeTecnica) {
		boolean habilidadeTecnicaJaExiste = habilidadeTecnicaRepository.findByNome(habilidadeTecnica.getNome())
				.stream().anyMatch((habilidadeTecnicaExistente) -> !habilidadeTecnicaExistente.equals(habilidadeTecnica));
		if(habilidadeTecnicaJaExiste) {
			throw new EdtechException("Já existe uma Habilidade Tecnica com este nome, tente novamente!");
		}
		return habilidadeTecnicaRepository.save(habilidadeTecnica);
	}
	
	public List<HabilidadeTecnica> listarTodasHabilidadesTecnicas(){
		return habilidadeTecnicaRepository.findAll();
	}
	
	public HabilidadeTecnica buscarHabilidadeTecnicaById(Long idHabilidadeTecnica) {
		return habilidadeTecnicaRepository.findById(idHabilidadeTecnica).orElseThrow(
				() -> new RecursoNaoEncontrado("Habilidade Técnica não encontrada ou inexistente!"));
	}
	
	public boolean existeHabilidadeTecnicaById(Long idHabilidadeTecnica) {
		return habilidadeTecnicaRepository.existsById(idHabilidadeTecnica);
	}
	
	public void deletarHabilidadeTecnicaById(Long idHabilidadeTecnica) {
		if(!existeHabilidadeTecnicaById(idHabilidadeTecnica)) {
			throw new RecursoNaoEncontrado("Habilidade Técnica não encontrada ou inexistente!");
		}
		habilidadeTecnicaRepository.deleteById(idHabilidadeTecnica);
	}
	
	
}
