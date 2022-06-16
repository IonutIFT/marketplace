package edu.es.eoi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.es.eoi.dto.ArticuloDto;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.repository.ArticuloRepository;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	ArticuloRepository articleRepo;

	public ArticuloDto findById(int id) {

		return convertToDto(articleRepo.findById(id).get());

	}


	public List<ArticuloDto> findByName(String name) {


		List<Articulo> articulos = articleRepo.findByNameContainingIgnoreCase(name);
		List<ArticuloDto> dtos = new ArrayList<ArticuloDto>();
		
		for (Articulo articulo : articulos) {
				dtos.add(convertToDto(articulo));
		}
		return dtos;
	}

	public List<ArticuloDto> findAll() {

		List<Articulo> articulos = articleRepo.findAll();
		List<ArticuloDto> dtos = new ArrayList<ArticuloDto>();

		for (Articulo articulo : articulos) {
			dtos.add(convertToDto(articulo));
		}
		return dtos;
	}

	public Articulo create(ArticuloDto dto) {

		return articleRepo.save(convertToEntity(dto));

	}

	public void delete(ArticuloDto dto) {

		articleRepo.deleteById(dto.getId());

	}

	public ArticuloDto update(ArticuloDto dto, Integer id) {
		Articulo article = articleRepo.findById(id).get();

		article.setName(dto.getName());
		article.setPrice(dto.getPrice());
		article.setStock(dto.getStock());

		articleRepo.save(article);
		return dto;

	}

	private ArticuloDto convertToDto(Articulo entity) {

		ArticuloDto dto = new ArticuloDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setStock(entity.getStock());
		return dto;
	}

	private Articulo convertToEntity(ArticuloDto dto) {

		Articulo entity = new Articulo();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		entity.setStock(dto.getStock());
		return entity;

	}

}
