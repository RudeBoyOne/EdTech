package com.edtech_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class HabilidadeTecnica {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idHabilidadeTecnica;

	@NonNull
	private String nome;
	
	@ManyToMany(mappedBy = "habilidadesTecnicas")
    private List<Professor> professores = new ArrayList<>();

}
