package edu.es.eoi.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.CestaDto;
import edu.es.eoi.dto.PedidoDto;
import edu.es.eoi.entity.Cesta;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.repository.ArticuloRepository;
import edu.es.eoi.repository.PedidoRepository;
import edu.es.eoi.repository.UsuarioRepository;

@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	PedidoRepository orderRepo;
	
	@Autowired
	ArticuloRepository articleRepo;
	
	@Autowired
	UsuarioRepository userRepo;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	public PedidoDto findById(int id) {
		
		Pedido entity = orderRepo.findById(id).get();
		PedidoDto dto = convertToDto(entity);
		return dto;
		
	}
	
	public List<PedidoDto> findByName(String name) {

		List<Pedido> pedidos = orderRepo.findByNameContainingIgnoreCase(name);
		List<PedidoDto> dtos = new ArrayList<PedidoDto>();
				
		for (Pedido pedido : pedidos) {
				dtos.add(convertToDto(pedido));
		}
		return dtos;
	}
	
	public List<PedidoDto> findAll() {
		
		List<Pedido> pedidos = orderRepo.findAll();
		List<PedidoDto> dtos = new ArrayList<PedidoDto>();
		
		
		
		for (Pedido pedido : pedidos) {
			List<Cesta> cesta = pedido.getCesta();
			List<CestaDto> cestaDto = new ArrayList();
			
			PedidoDto dto = new PedidoDto();
			dto.setId(pedido.getId());
			dto.setFecha(pedido.getFecha().format(formatter));
			dto.setName(pedido.getName());
			dto.setUserId(Integer.toString(pedido.getUser().getId()));
			for(Cesta article : cesta) {
				CestaDto dtoTmp = new CestaDto();
				dtoTmp.setId(article.getId());
				dtoTmp.setAmount(article.getAmount());
				cestaDto.add(dtoTmp);
			}
			dto.setCesta(cestaDto);
			dtos.add(dto);
		}
		return dtos;
	}
	
	public Pedido create(PedidoDto dto) {
		
		return orderRepo.save(convertToEntity(dto));
//		return dto;
		
	}
	
	public void delete(PedidoDto dto) {
		
		orderRepo.deleteById(dto.getId());
		
	}
	
	public PedidoDto update (PedidoDto dto, Integer id) {
		
		
		Pedido entity = orderRepo.findById(id).get();
		List<CestaDto> cestaDto = dto.getCesta();
		List<Cesta> cesta = new ArrayList<>();
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		if(dto.getFecha() != null) {
		entity.setFecha(LocalDate.parse(dto.getFecha(), formatter));
		} else {
			entity.setFecha(LocalDate.now());
			dto.setFecha(formatter.format(entity.getFecha()));
		}
		
		entity.setName(dto.getName());
		
		for(CestaDto article : cestaDto) {
			Cesta entityTmp = new Cesta();
			entityTmp.setArticle(articleRepo.findById(article.getId()).get());
			entityTmp.setAmount(article.getAmount());
			cesta.add(entityTmp);
		}
		entity.setCesta(cesta);
		orderRepo.save(entity);
		
		return dto;
		
	}
	
	private PedidoDto convertToDto(Pedido entity) {
		
		PedidoDto dto = new PedidoDto();
		List<Cesta> cesta = entity.getCesta();
		List<CestaDto> cestaDto = new ArrayList<>();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setUserId(Integer.toString(entity.getUser().getId()));
		if(entity.getFecha() != null) {
//		dto.setFecha(entity.getFecha().format(formatter));
		dto.setFecha(formatter.format(entity.getFecha()));
		} else {
			dto.setFecha(formatter.format(LocalDate.now()));
//			dto.setFecha(entity.getFecha().format(formatter));
		}

		for (Cesta article : cesta) {
			CestaDto dtoTmp = new CestaDto();
			dtoTmp.setId(article.getArticle().getId());
			dtoTmp.setAmount(article.getAmount());
			cestaDto.add(dtoTmp);
		}
		
		dto.setCesta(cestaDto);
		return dto;
	}
	
	private Pedido convertToEntity(PedidoDto dto) {
		
		Pedido entity = new Pedido();
		List<CestaDto> cestaDto = dto.getCesta();
		List<Cesta> cesta = new ArrayList<>();
		//Pedido entity = orderRepo.findById(id).get();
		//PedidoDto dto = convertToDto(entity);
		
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		//dto.setUserId(Integer.toString(entity.getId()));
		//entity.setUser(orderRepo.findById(dto.getId()).get());
//		dto.setUserId(Integer.toString(entity.getUser().getId()));
		entity.setUser(userRepo.findById(Integer.parseInt(dto.getUserId())).get());;
		if(dto.getFecha() != null) {
//		dto.setFecha(entity.getFecha().format(formatter));
		entity.setFecha(LocalDate.parse(dto.getFecha(), formatter));
		} else {
			entity.setFecha(LocalDate.now());
			dto.setFecha(formatter.format(entity.getFecha()));
//			dto.setFecha(entity.getFecha().format(formatter));
//			entity.setFecha(LocalDate.parse(dto.getFecha(), formatter));
		}
		
		for(CestaDto article : cestaDto) {
			Cesta entityTmp = new Cesta();
			entityTmp.setArticle(articleRepo.findById(article.getId()).get());
			entityTmp.setAmount(article.getAmount());
			cesta.add(entityTmp);
		}
		entity.setCesta(cesta);
		return entity;
		
	}
	
}
