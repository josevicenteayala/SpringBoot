package co.com.intergrupo.maven.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.com.intergrupo.maven.boot.model.Persona;
import co.com.intergrupo.maven.boot.service.ExampleService;

@Controller
@RequestMapping("/example")
public class ExampleController {

	public static final String VISTA_EXAMPLE = "example";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class); 
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;	

	/**
	 * Es usado cuando la informacion a mostrar es poca
	 * @return la plantilla a mostrar en pantalla
	 * http://localhost:8080/example/exampleString
	 */
	@GetMapping(value="/exampleString")
	public String example(Model model) {
		model.addAttribute("nombre","Jose Vicente Ayala Luna");	
		Persona persona = new Persona("Isabel Ayala Luna", 8);
		model.addAttribute(persona);
		model.addAttribute("personas",obtenerListaPersonas());
		
		return VISTA_EXAMPLE;
	}

	
	/**
	 * Metodo usado para retornar a la vista, gran cantidad de informacion
	 * @return un objeto de la vista con bastante informacion a retornar
	 * http://localhost:8080/example/exampleModelView
	 */
	@GetMapping(value="/exampleModelView")
	public ModelAndView exampleModelView() {
		ModelAndView modelAndView = new ModelAndView(VISTA_EXAMPLE);
		modelAndView.addObject("nombre", "Martha Cristina Dudue Duque");
		Persona persona = new Persona("Jose Vicente Ayala Luna", 42);
		modelAndView.addObject(persona);
		
		modelAndView.addObject("personas",obtenerListaPersonas());
		
		return modelAndView;
	}
	
	private List<Persona> obtenerListaPersonas(){
		LOGGER.info("OBTENIENDO LA LISTA DE PERSONAS");
		return exampleService.getListaPersonas();
	}
}
