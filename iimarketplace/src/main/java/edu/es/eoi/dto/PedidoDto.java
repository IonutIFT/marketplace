package edu.es.eoi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {
	
	private Integer id;
	
	@JsonProperty(value = "nombre")
	private String name;
	
	private String fecha;
	
	@JsonProperty(value = "articulos")
	private List<CestaDto> cesta;

}
