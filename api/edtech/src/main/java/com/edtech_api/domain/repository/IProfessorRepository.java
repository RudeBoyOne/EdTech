package com.edtech_api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edtech_api.domain.model.Professor;

@Repository
@Transactional
public interface IProfessorRepository extends JpaRepository<Professor, Long>{

	Optional<Professor> findByNome(String nome);
	Optional<Professor> findByEmail(String email);
}
