package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.dto.UsuarioDto;
import edu.es.eoi.entity.Usuario;

public interface UsuarioService {

	UsuarioDto findById(int id);
	
	List<UsuarioDto> findAll();
	
	Usuario create(UsuarioDto dto);
	
	UsuarioDto update(UsuarioDto dot, Integer id);
	
	UsuarioDto loggin(UsuarioDto dto);
	
	void delete(UsuarioDto dto);
	
	
}
