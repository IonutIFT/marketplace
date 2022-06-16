package edu.es.eoi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cesta")
public class Cesta {

	@Id
	@Column(name = "id_cesta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "cantidad")
	private Integer amount;
		
	@ManyToOne(targetEntity = Articulo.class, cascade = CascadeType.ALL)
	private Articulo article;
	
	@ManyToOne(targetEntity = Pedido.class, cascade = CascadeType.ALL)
	private Pedido order;
	

}
