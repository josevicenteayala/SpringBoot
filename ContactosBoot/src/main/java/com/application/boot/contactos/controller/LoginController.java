package com.application.boot.contactos.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.boot.contactos.constant.ViewConstant;
import com.application.boot.contactos.model.UserCredential;
import com.application.boot.contactos.service.ContactService;


@Controller
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	private static final String REDIRECT_LOGIN_ERROR = "redirect:/login?error=Error de ingreso";
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/")
	public String redireccionarAlLogin() {
		return ViewConstant.REDIRECT_LOGIN;
	}
	
	@GetMapping("/login")
	public String mostrarLogin(Model model, 
								@RequestParam(name="error", defaultValue="", required=false) String error,
								@RequestParam(name="logout", required=false) String logout) {
		model.addAttribute("userCredentials",new UserCredential());
		
		LOGGER.info("EL ERROR EN EL LOGIN ES "+error);
		LOGGER.info("EL LOGOUT EN EL LOGIN ES "+logout);
		model.addAttribute("error",error);
		model.addAttribute("logout",logout);
		return ViewConstant.PAGINA_LOGIN;
	}
	
	@PostMapping("/loginCheck")
	public String loginCheck(Model model,@ModelAttribute(name="userCredentials") com.application.boot.contactos.model.UserCredential userCredential) {
		Objects.requireNonNull(userCredential, "Las credenciales no pueden ser vacias");
		if(userCredential.getUserName().equals("user") && userCredential.getPassword().equals("user")) {
			model.addAttribute("listaContactos",contactService.consultarListaContactos());
			return ViewConstant.PAGINA_CONTACTS;
		}
		return REDIRECT_LOGIN_ERROR;
	}
	
}
