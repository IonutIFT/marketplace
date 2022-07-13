package edu.es.eoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.PedidoDto;
import edu.es.eoi.dto.UsuarioDto;
import edu.es.eoi.entity.Usuario;
import edu.es.eoi.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository userRepo;

	public UsuarioDto findById(int id) {

		return convertToDto(userRepo.findById(id).get());
	}

	public List<UsuarioDto> findAll() {

		List<Usuario> users = userRepo.findAll();
		List<UsuarioDto> dtos = new ArrayList<UsuarioDto>();

		for (Usuario user : users) {
			dtos.add(convertToDto(user));
		}
		return dtos;
	}

	public Usuario create(UsuarioDto dto) {

		return userRepo.save(convertToEntity(dto));

	}

	public UsuarioDto update(UsuarioDto dto, Integer id) {
	
		Usuario user = userRepo.findById(id).get();
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		
		userRepo.save(user);
		return dto;
		
	}

	public UsuarioDto loggin(UsuarioDto dto) {

		Usuario entity = userRepo.findByNameAndPassword(dto.getName(), dto.getPassword());

		if (entity != null) {
			return convertToDto(entity);
		} else {
			return null;
		}

	}
	
	public void delete(UsuarioDto dto) {
		
		userRepo.deleteById(dto.getId());
		
	}

	private UsuarioDto convertToDto(Usuario entity) {

		UsuarioDto dto = new UsuarioDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		return dto;
	}

	private Usuario convertToEntity(UsuarioDto dto) {

		Usuario user = new Usuario();
		user.setId(dto.getId());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		return user;

	}

}
