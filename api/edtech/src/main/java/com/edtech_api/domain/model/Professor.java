package com.edtech_api.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
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
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idCarreira")
	private Carreira carreira;
	
	@NonNull
	@ManyToMany
	@JoinTable(	name= "professor_has_habilidade", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "idProfessor"),
				inverseJoinColumns = @JoinColumn(name= "idHabilidadeTecnica", referencedColumnName = "idHabilidadeTecnica"))
	private List<HabilidadeTecnica> habilidadesTecnicas;
	
	
	@ManyToMany
	@JoinTable( name= "professor_has_bootcamp", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "idProfessor"),
				inverseJoinColumns = @JoinColumn(name= "idBootcamp", referencedColumnName = "idBootcamp"))
	private List<Bootcamp> bootcamps = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name= "idExtensao")
	private Extensao extensao;

	
}
