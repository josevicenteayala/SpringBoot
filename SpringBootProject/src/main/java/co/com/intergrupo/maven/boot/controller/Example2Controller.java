package co.com.intergrupo.maven.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/example2")
public class Example2Controller {

	private static final String VISTA_EXAMPLE2 = "example2";

	/**
	 * @param nombre con el nombre de la persona
	 * @return un objeto que contiene la informacion a mostrar en la vista
	 * http://localhost:8080/example2/peticionGet?nombreCompleto=Jose%20Vicente
	 */
	@GetMapping("/peticionGet")
	public ModelAndView peticionPorGet(@RequestParam(name="nombreCompleto",required=false,defaultValue="No se envio el nombre") String nombre) {
		ModelAndView modelAndView = new ModelAndView(VISTA_EXAMPLE2);
		modelAndView.addObject("nombreDesdeElModelo",nombre);
		return modelAndView;
	}
	
	/**
	 * @param nombre con el nombre de la persona
	 * @return un objeto que contiene la informacion a mostrar en la vista
	 * http://localhost:8080/example2/peticionGet2/Jose%20Vicente
	 */
	@GetMapping("/peticionGet2/{nombreCompleto}")
	public ModelAndView peticionPorGet2(@PathVariable("nombreCompleto") String nombre) {
		ModelAndView modelAndView = new ModelAndView(VISTA_EXAMPLE2);
		modelAndView.addObject("nombreDesdeElModelo",nombre);
		return modelAndView;		
	}
	
	
	
}
