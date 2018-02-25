package co.com.intergrupo.maven.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import co.com.intergrupo.maven.boot.model.Persona;

@Controller()
@RequestMapping("/example3")
public class Example3Controller {

	
	public static final String VISTA_RESULTADO_PERSONA = "/resultadoPersona";
	public static final String VISTA_FORMULARIO = "formulario";

	@GetMapping("/*")
	public String redireccion() {
		return "redirect:/example3/mostrarFormulario";
	}
	
	@GetMapping("/")
	public RedirectView redireccion2() {
		return new RedirectView("/example3/mostrarFormulario");
	}
	
	/**
	 * @param model
	 * @return
	 * http://localhost:8080/example3/mostrarFormulario
	 */
	@GetMapping("/mostrarFormulario")
	public String mostrarFormulario(Model model) {
		model.addAttribute(new Persona());
		return VISTA_FORMULARIO;
	}
	
	@PostMapping("/datosDeLaPersona")
	public ModelAndView recibirParametrosDedeElFormulario(@ModelAttribute("persona") Persona persona) {
		ModelAndView modelAndView = new ModelAndView(VISTA_RESULTADO_PERSONA);
		modelAndView.addObject("persona",persona);
		return modelAndView;
	}
}
