package edu.es.eoi;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.es.eoi.dto.UsuarioDto;
import edu.es.eoi.entity.Articulo;
import edu.es.eoi.entity.Pedido;
import edu.es.eoi.entity.Usuario;
import edu.es.eoi.repository.ArticuloRepository;
import edu.es.eoi.repository.PedidoRepository;
import edu.es.eoi.repository.UsuarioRepository;
import edu.es.eoi.service.ArticuloService;
import edu.es.eoi.service.PedidoService;
import edu.es.eoi.service.UsuarioService;

@SpringBootTest
class IimarketplaceApplicationTests {

	@Autowired
	UsuarioRepository userRepo;
	
	@Autowired
	UsuarioService userService;
	
	@Autowired
	ArticuloRepository articleRepo;
	
	@Autowired
	ArticuloService articleService;
	
	@Autowired
	PedidoRepository orderRepo;
	
	@Autowired
	PedidoService orderService;
	
	
	@Test
	void usersTest() {

		Usuario tmp = new Usuario();
		tmp.setName("Test30");
		tmp.setPassword("passwd30");
		
		userRepo.save(tmp);
		
		Usuario test = userRepo.findById(tmp.getId()).get();
		Assertions.assertEquals("Test30", test.getName());
		
		test.setPassword("updatedPasswd");
		userRepo.save(test);
		
		Assertions.assertEquals("updatedPasswd", userRepo.findById(test.getId()).get().getPassword());
		
		UsuarioDto dto = new UsuarioDto();
		dto.setId(tmp.getId());
		dto.setName(tmp.getName());
		dto.setPassword(test.getPassword());
	
		

		UsuarioDto loggin = userService.loggin(dto);		
		System.out.println(loggin.getName());
		Assertions.assertNotNull(loggin);

		
		userRepo.deleteById(test.getId());
		Optional<Usuario> deleted = userRepo.findById(test.getId());
		
		Assertions.assertFalse(deleted.isPresent());
		
	}
	
	@Test
	void articlesTest() {
		
		Articulo tmp2 = new Articulo();
		tmp2.setName("Articulo_test");
		tmp2.setPrice(100.0);
		tmp2.setStock(0);
		
		articleRepo.save(tmp2);
		
		Articulo test2 = articleRepo.findById(tmp2.getId()).get();
		Assertions.assertEquals("Articulo_test", test2.getName());
		test2.setStock(20);
		articleRepo.save(test2);
		Assertions.assertEquals(20, articleRepo.findById(test2.getId()).get().getStock());
		
		Assertions.assertEquals("articulo_test", articleService.findByName("articulo_test").get(0).getName());
		
		articleRepo.deleteById(test2.getId());
		Optional<Articulo> deleted = articleRepo.findById(test2.getId());
		
		Assertions.assertFalse(deleted.isPresent());
		
		
	}

	@Test
	void ordersTest() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd");
		
		Pedido tmp3 = new Pedido();
		tmp3.setName("Pedido_test");
		
		tmp3.setFecha(LocalDate.now());
		
		orderRepo.save(tmp3);
		
		Pedido test3 = orderRepo.findById(tmp3.getId()).get();
		
		test3.setFecha(LocalDate.of(2022, 2, 2));
		orderRepo.save(test3);
		
		test3.setUser(userRepo.findById(1).get());
		System.out.println(test3.getUser().getPassword());
				
		Assertions.assertEquals(LocalDate.of(2022, 2, 2), orderRepo.findById(test3.getId()).get().getFecha());
		
		orderRepo.deleteById(test3.getId());
		
		Optional<Pedido> deleted2 = orderRepo.findById(test3.getId());
		Assertions.assertFalse(deleted2.isPresent());
		
	}

}
