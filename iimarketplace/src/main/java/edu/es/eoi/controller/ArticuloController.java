package edu.es.eoi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.ArticuloDto;
import edu.es.eoi.dto.PedidoDto;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.repository.ArticuloRepository;
import edu.es.eoi.service.ArticuloService;

@RestController
@RequestMapping("/marketplace/articulos")
public class ArticuloController {

	@Autowired
	private ArticuloService service;

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		
		return new ResponseEntity<List<ArticuloDto>>(service.findAll(),HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/nombre/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> findArticuloByName(@PathVariable String nombreparcial) {
		
		List<ArticuloDto> dtotmp = service.findByName(nombreparcial);
		
		if(dtotmp != null) {

			return new ResponseEntity<List<ArticuloDto>>(service.findByName(nombreparcial),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findArticuloById(@PathVariable Integer id) {
		
		if(service.findById(id) != null) {
			return new ResponseEntity<ArticuloDto>(service.findById(id),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createArticulo(@RequestBody ArticuloDto article) {
		
		if(article.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else  {
			return new ResponseEntity<>(service.create(article), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticulo(@RequestBody ArticuloDto article, @PathVariable Integer id) {
		
		if(article.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
		return new ResponseEntity<ArticuloDto>(service.update(article, id),HttpStatus.ACCEPTED);
		}
	}

}
