package edu.es.eoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.UsuarioDto;
import edu.es.eoi.service.UsuarioService;

@RestController
@RequestMapping("/marketplace/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		
		return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findUsuario(@PathVariable Integer id) {
		
		
		UsuarioDto dto = service.findById(id);
		
		if(dto != null) {
			return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUsuario(@RequestBody UsuarioDto user) {
		
		if(user.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else  {
			return new ResponseEntity<>(service.create(user), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(value = "loggin", method = RequestMethod.POST)
	public ResponseEntity<?> logginUser(@RequestBody UsuarioDto dto) {
		
		if (service.loggin(dto) != null) {
			return new ResponseEntity<UsuarioDto>(service.findById(service.loggin(dto).getId()),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateClient(@RequestBody UsuarioDto user, @PathVariable Integer id) {
		
		if(user.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
		return new ResponseEntity<UsuarioDto>(service.update(user, id) ,HttpStatus.ACCEPTED);
		}
	}

}


