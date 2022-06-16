package edu.es.eoi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pedido")
public class Pedido {

	@Id
	@Column(name = "id_pedido")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "fecha")
	private LocalDate fecha;
	
	@Column(name = "nombre")
	private String name;
	
	@OneToMany(mappedBy = "order", targetEntity = Cesta.class, cascade = CascadeType.ALL)
	private List<Cesta> cesta;
		
	@ManyToOne(targetEntity = Usuario.class)
	private Usuario user;
}
