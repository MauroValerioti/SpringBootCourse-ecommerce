package com.course.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.ecommerce.model.DetalleOrden;
import com.course.ecommerce.model.Orden;
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

	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

	// datos de la orden
	Orden orden = new Orden();

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

		model.addAttribute("producto", producto); // el 1er atributo es el nombre que tendra en la vista y el 2do es la
													// variable que le paso.

		return "usuario/productohome";
	}

	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) { // estos parametros
																									// son para ir a
																									// buscar del
																									// producto el id
																									// que le correponde
																									// y la cantidad a
																									// comprar.
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
		double sumaTotal = 0;

		Optional<Producto> optionalProducto = productoService.get(id);
		log.info("producto anadido:  {}", optionalProducto.get());
		log.info("cantidad: {}", cantidad);

		producto = optionalProducto.get();

		detalleOrden.setCantidad(cantidad);
		detalleOrden.setPrecio(producto.getPrecio());
		detalleOrden.setNombre(producto.getNombre());
		detalleOrden.setTotal(producto.getPrecio() * cantidad);
		detalleOrden.setProducto(producto); // producto al que esta vinculado la orden

		detalles.add(detalleOrden);

		sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum(); // funcion lambda para sumar todos los
																				// productos que esten en la lista

		orden.setTotal(sumaTotal);
		model.addAttribute("cart", detalles); // agrega todos los detalles que va pasando el usuario
		model.addAttribute("orden", orden);
		return "usuario/carrito";
	}
}
