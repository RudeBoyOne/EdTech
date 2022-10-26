package com.edtech_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edtech_api.domain.model.HabilidadeTecnica;

@Repository
@Transactional
public interface IHabilidadeTecnicaRepository extends JpaRepository<HabilidadeTecnica, Long>{

}
