package com.edtech_api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edtech_api.domain.model.Carreira;

@Repository
@Transactional
public interface ICarreiraRepository extends JpaRepository<Carreira, Long>{

	Optional<Carreira> findByNome(String nome);
}
