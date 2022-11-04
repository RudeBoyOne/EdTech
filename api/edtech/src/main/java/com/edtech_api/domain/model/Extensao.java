package com.edtech_api.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.edtech_api.domain.model.enums.StatusExtensao;

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
public class Extensao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idExtensao;
	
	@NonNull
	private String nome;
	
	@NonNull
	private String especialidade;
	
	@NonNull
	private LocalDate dataInicio;
	
	@NonNull
	private LocalDate dataTermino;
	
	@Column
	@NonNull
	@Enumerated(EnumType.STRING)
	private StatusExtensao status;
	
	@OneToMany(mappedBy = "extensao")
	private List<Professor> professores = new ArrayList<>();
}
