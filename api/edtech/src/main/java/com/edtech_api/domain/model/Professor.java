package com.edtech_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class Professor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProfessor;

	@NonNull
	private String nome;
	
	@NonNull
	private String email;
	
	@NonNull
	@ManyToMany
	@JoinTable(	name= "professor_x_habilidade", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "idProfessor"),
				inverseJoinColumns = @JoinColumn(name= "idHabilidadeTecnica", referencedColumnName = "idHabilidadeTecnica"))
	private List<HabilidadeTecnica> habilidadesTecnicas = new ArrayList<>();
	
	@NonNull
	@ManyToOne
	@JoinColumn(name="idCarreira")
	private Carreira carreira;
	
	@ManyToMany
	@JoinTable( name= "professor_x_bootcamp", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "idProfessor"),
				inverseJoinColumns = @JoinColumn(name= "idBootcamp", referencedColumnName = "idBootcamp"))
	private List<Bootcamp> bootcamps = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name= "idExtensao")
	private Extensao extensao;
	
}
