package edu.es.eoi.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import edu.es.eoi.entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "from Usuario where nombre like: name")
	public List<Usuario> findByNameContaining(String name);
	public Usuario findByNameAndPassword(String name, String password);
	
}
