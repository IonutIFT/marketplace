package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.dto.ArticuloDto;
import edu.es.eoi.entity.Articulo;

public interface ArticuloService {

	ArticuloDto findById(int id);
	
	List<ArticuloDto> findAll();
	
	Articulo create(ArticuloDto dto);
	
	void delete(ArticuloDto dto);
	
	ArticuloDto update(ArticuloDto dto, Integer id);

	List<ArticuloDto> findByName(String name);
	
	
}
