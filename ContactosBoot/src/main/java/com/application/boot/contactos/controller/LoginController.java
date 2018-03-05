package com.application.boot.contactos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.boot.contactos.constant.ViewConstant;
import com.application.boot.contactos.model.UserCredential;


// TODO: Auto-generated Javadoc
/**
 * The Class LoginController.
 */
@Controller
public class LoginController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * Mostrar login.
	 *
	 * @param model the model
	 * @param error the error
	 * @param logout the logout
	 * @return the string
	 */
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
	
	/**
	 * Login success.
	 *
	 * @return the string
	 */
	@GetMapping({"/loginSuccess","/"})
	public String loginSuccess() {		
		return ViewConstant.REDIRECT_CONTACTS_SHOW_CONTACTS;
	}	
	
}
