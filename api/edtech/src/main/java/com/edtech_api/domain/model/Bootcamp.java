package com.edtech_api.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Bootcamp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBootcamp;

	@NonNull
	private String nome;
	
	@NonNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idCarreira")
	private Carreira carreira;
	
	@NonNull
	private LocalDate dataInicio;
	
	@NonNull
	private LocalDate dataTermino;
	
	@ManyToMany(mappedBy = "bootcamps")
	private List<Professor> professores = new ArrayList<>();
}
