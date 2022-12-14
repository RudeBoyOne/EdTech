package com.edtech_api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edtech_api.domain.model.Bootcamp;

@Repository
@Transactional
public interface IBootcampRepository extends JpaRepository<Bootcamp, Long> {

	Optional<Bootcamp> findByNome(String nome);
}
