package edu.es.eoi.service;

import java.util.List;

import edu.es.eoi.dto.PedidoDto;
import edu.es.eoi.entity.Pedido;

public interface PedidoService {

	PedidoDto findById(int id);
	
	List<PedidoDto> findAll();
	
	PedidoDto create(PedidoDto dto);
	
	void delete(PedidoDto dto);
	
	PedidoDto update(PedidoDto dto, Integer id);
	
	List<PedidoDto> findByName(String name);
	
	
	
}
