package edu.es.eoi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.es.eoi.dto.PedidoDto;
import edu.es.eoi.service.PedidoService;


@CrossOrigin
@RestController
@RequestMapping("/marketplace/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> findAll(){
		
		return new ResponseEntity<>(service.findAll(),HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findPedido(@PathVariable Integer id) {
		
		
		PedidoDto dto = service.findById(id);
		
		if(dto != null) {
			return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletePedido(@PathVariable Integer id) {
		PedidoDto pedido = service.findById(id);
		
		if(service.findById(id) != null) {
			service.delete(pedido);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createPedido(@RequestBody PedidoDto order) {
		
		if(order.getId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else  {
			return new ResponseEntity<>(service.create(order), HttpStatus.CREATED);
		}
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updatePedido(@RequestBody PedidoDto order, @PathVariable Integer id) {
		
		if(order.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<PedidoDto>(service.update(order, id),HttpStatus.ACCEPTED);
		}
	}
	
	@RequestMapping(value = "/nombre/{nombreparcial}", method = RequestMethod.GET)
	public ResponseEntity<?> findPedidoByName(@PathVariable String nombreparcial) {
		
		List<PedidoDto> dtotmp = service.findByName(nombreparcial);
		
		if(dtotmp != null) {
			return new ResponseEntity<List<PedidoDto>>(service.findByName(nombreparcial),HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
