package com.edtech_api.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.edtech_api.domain.model.enums.StatusBootcamp;

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
	@Column(name = "id")
	private Long idBootcamp;

	@NonNull
	private String nome;
	
	@NonNull
	@ManyToOne
	@JoinColumn(name = "idCarreira")
	private Carreira carreira;
	
	@NonNull
	private LocalDate dataInicio;
	
	@NonNull
	private LocalDate dataTermino;
	
	@Column
	@NonNull
	@Enumerated(EnumType.STRING)
	private StatusBootcamp status;
	
	@ManyToMany(mappedBy = "bootcamps")
	private List<Professor> professores = new ArrayList<>();
}
