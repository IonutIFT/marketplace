package edu.es.eoi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ArticuloDto {

	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String name;
	
	@JsonProperty(value = "precio")
	private Double price;

	private Integer stock;
	
}
