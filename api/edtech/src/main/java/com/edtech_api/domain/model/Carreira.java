package com.edtech_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Carreira {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCarreira;

	@NonNull
	private String nome;
	
	@NonNull
	private Integer duracao;
	
	@NonNull
	private Integer duracaoProjetoFinal;
	
	@OneToMany(mappedBy = "carreira")
	private List<Bootcamp> bootcamps = new ArrayList<>();
	
	@OneToMany(mappedBy = "carreira")
	private List<Professor> professores = new ArrayList<>();
}
