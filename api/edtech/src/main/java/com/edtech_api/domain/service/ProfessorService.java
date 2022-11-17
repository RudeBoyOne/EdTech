package com.edtech_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edtech_api.domain.exceptions.EdtechException;
import com.edtech_api.domain.exceptions.RecursoNaoEncontrado;
import com.edtech_api.domain.model.Professor;
import com.edtech_api.domain.repository.IProfessorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfessorService {

	private IProfessorRepository professorRepository;
	
	public Professor criarProfessor(Professor professor) {
		boolean professorJaExiste = professorRepository.findByEmail(professor.getEmail())
				.stream().anyMatch((professorExistente) -> !professorExistente.equals(professor));
		
		if(professorJaExiste) {
			throw new EdtechException("Já existe um Professor com este email, tente novamente");
		}
		
		return professorRepository.save(professor);
	}
	
	public List<Professor> listarTodosProfessores(){
		return professorRepository.findAll();
	}
	
	public Professor buscarProfessorById(Long idProfessor) {
		return professorRepository.findById(idProfessor).orElseThrow(
				() -> new RecursoNaoEncontrado("Professor não encontrado ou inexistente!"));
	}
	
	public boolean existeProfessorById(Long idProfessor) {
		return professorRepository.existsById(idProfessor);
	}
	
	public void deletarProfessorById(Long idProfessor) {
		if(!existeProfessorById(idProfessor)) {
			throw new RecursoNaoEncontrado("Professor não encontrado ou inexistente!");
		}
		professorRepository.deleteById(idProfessor);
	}
}
