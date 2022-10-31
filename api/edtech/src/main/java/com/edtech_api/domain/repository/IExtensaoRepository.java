package com.edtech_api.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edtech_api.domain.model.Extensao;

@Repository
@Transactional
public interface IExtensaoRepository extends JpaRepository<Extensao, Long>{

	Optional<Extensao> findByNome(String nome);
}
