package com.edtech_api.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@Column(name = "id")
	private Long idProfessor;

	@NonNull
	private String nome;
	
	@NonNull
	private String email;
	
	@NonNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idCarreira")
	private Carreira carreira;
	
	@NonNull
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(	name= "professor_has_habilidade", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name= "idHabilidadeTecnica", referencedColumnName = "id"))
	private List<HabilidadeTecnica> habilidadesTecnicas;
	
	@ManyToMany
	@JoinTable( name= "professor_has_bootcamp", joinColumns = @JoinColumn(name="idProfessor", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(name= "idBootcamp", referencedColumnName = "id"))
	private List<Bootcamp> bootcamps;
	
	@ManyToOne
	@JoinColumn(name= "idExtensao")
	private Extensao extensao;

	
}
