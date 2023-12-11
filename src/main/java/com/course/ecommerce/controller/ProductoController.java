package com.course.ecommerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.course.ecommerce.model.Producto;
import com.course.ecommerce.model.Usuario;
import com.course.ecommerce.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	// este atributo me permite ver como INFO los productos que voy cargando en la
	// consola del IDE
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

	@Autowired // con el @AutoWired spring se encarga de instanciar la variable no es
				// neceesario que lo hagamos nosotros
	private ProductoService productoService;

	@GetMapping("") // las comillas vacias van para que se mapee directamente hacia productos
	public String show(Model model) { // model traera el elemento del backend hacia la vista
		// contiene 2 parametros, el primer es el nombre con el que traere la info y el
		// 2do es los elementos que voy a recibir
		model.addAttribute("productos", productoService.findAll());

		return "productos/show";
	}

	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}

	@PostMapping("/save")
	public String save(Producto producto) {
		LOGGER.info("Este es el objeto producto {}", producto);
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");

		producto.setUsuario(u);

		productoService.save(producto);
		return "redirect:/productos";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id) { //@PathVariable nos permite mapear la variable que viene a la url.
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id); //esto es lo que devuelve cuando hago la consulta por un elemento de tipo producto.
		producto = optionalProducto.get();
		
		LOGGER.info("Producto buscado: {}", producto); //el LOGGER me permitira ver en la consola la variable que va a venir dentro de producto.
		
		return "productos/edit";
	}
	
//	@GetMapping("/edit/{id}")
//	public String edit(@PathVariable Integer id, Model model) {
//	    Optional<Producto> optionalProducto = productoService.get(id);
//	    
//	    if (optionalProducto.isPresent()) {
//	        Producto producto = optionalProducto.get();
//	        LOGGER.info("Producto buscado: {}", producto);
//	        model.addAttribute("producto", producto);  // Agrega el producto al modelo con el nombre "producto"
//	        return "productos/edit";
//	    } else {
//	        // Puedes manejar el caso en que el producto no se encuentra, por ejemplo, redirigiendo a una página de error.
//	        return "redirect:/error";
//	    }
//	}

}
