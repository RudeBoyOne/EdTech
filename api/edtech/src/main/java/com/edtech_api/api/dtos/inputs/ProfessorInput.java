package com.edtech_api.api.dtos.inputs;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Getter
@Setter
@AllArgsConstructor
public class ProfessorInput {

	@NotBlank
	@Size(max = 60)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 100)
	private String email;
	
	@NotNull
	@NotEmpty
	private List<Long> habilidades;
	
	@NotNull
	private Long carreira;
}
