package edu.es.eoi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "articulo")
public class Articulo {



	@Id
	@Column(name = "id_articulo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nombre")
	private String name;
	
	@Column(name = "precio")
	private Double price;
	
	@Column(name = "stock")
	private Integer stock;
	
	@OneToMany(mappedBy = "article", targetEntity = Cesta.class, cascade = CascadeType.ALL)
	private List<Cesta> cesta;
	
}
