package edu.es.eoi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String name;
	
	@JsonProperty(value = "password")
	private String password;
	
}
