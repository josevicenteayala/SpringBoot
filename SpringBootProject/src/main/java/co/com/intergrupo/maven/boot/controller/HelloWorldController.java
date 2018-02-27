package co.com.intergrupo.maven.boot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.com.intergrupo.maven.boot.component.ExampleComponent;
import co.com.intergrupo.maven.boot.model.Persona;
import co.com.intergrupo.maven.boot.service.ExampleService;

@Controller
@RequestMapping("/inicio")
public class HelloWorldController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
	@Qualifier("exampleComponent")
	private ExampleComponent ExampleComponent;
	
	@Autowired
	@Qualifier("exampleService")
	private ExampleService exampleService;
	
	@GetMapping("/helloworld")
	public String helloWorld() {
		ExampleComponent.llamandoUnMetodoDeUnComponente();
		
		LOGGER.info("INFO TRACE");
		LOGGER.warn("WARNINIG TRACE");
		LOGGER.error("ERROR TRACE");
		LOGGER.debug("DEBUG TRACER");	
		
		List<Persona> listaPersonas = exampleService.getListaPersonas();
		
		LOGGER.info("LA LISTA DE PERSONAS ES:  "+listaPersonas.toString());
		
		return "helloworld";
	}
	
}
