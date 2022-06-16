package edu.es.eoi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CestaDto {
	
	private Integer id;
	
	@JsonProperty(value = "cantidad")
	private Integer amount;

}
