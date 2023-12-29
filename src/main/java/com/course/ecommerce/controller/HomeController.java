package com.course.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.ecommerce.model.Producto;
import com.course.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/") // el '/' solo implica que va a apuntar a la raiz de la pagina
public class HomeController {

	// creo esta variable para que me tome la impresion por pantalla de las
	// variables que sean enviadas.
	private final Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired // la notacion autowired es para que inyecte el contenedor del framework
	private ProductoService productoService;

	@GetMapping("") // este metodo getMapping va a responder unicamente a la raiz
	public String home() {
		return "usuario/home"; // lo que hago aca es apuntar a la carpeta usuario y dentro de ella al archivo
								// home
	}

	@GetMapping("productohome/{id}") // aca le digo que este metodo va a responder a peticiones del tipo GetMapping y
										// va a ir a buscar el id del producto.
	public String productoHome(@PathVariable Integer id, Model model) { // con la noteacion @PathVariable el framework
																		// se da cuenta de que este valor id va a ser
																		// tomado de la url. //el objeto de tipo model
																		// nos permite llevar info a la vista.
		log.info("Id producto enviado por parametro {} ", id);

		Producto producto = new Producto();
		Optional<Producto> productoOptional = productoService.get(id);
		producto = productoOptional.get();
		
		model.addAttribute("producto", producto); //el 1er atributo es el nombre que tendra en la vista y el 2do es la variable que le paso.
		
		return "usuario/productohome";
	}
}
