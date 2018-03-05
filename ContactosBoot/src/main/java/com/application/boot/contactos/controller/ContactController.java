package com.application.boot.contactos.controller;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.boot.contactos.constant.ViewConstant;
import com.application.boot.contactos.model.ContactModel;
import com.application.boot.contactos.service.ContactService;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactController.
 */
@Controller
@RequestMapping("/contacts")
public class ContactController {

	/** The Constant OPERACION_NO_REALIZADA. */
	private static final int OPERACION_NO_REALIZADA = 0;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	/** The contact service. */
	@Autowired
	private ContactService contactService;
	
	/**
	 * Cancel.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/cancel")
	public String cancel(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return buscarListaContactos(model);
	}

	/**
	 * Redireccion A contact form.
	 *
	 * @param model the model
	 * @param idContacto the id contacto
	 * @return the string
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/contactForm")
	public String redireccionAContactForm(Model model, @RequestParam(name="idContacto", required=false) int idContacto) {
		if(idContacto == OPERACION_NO_REALIZADA) {
			model.addAttribute("contactModel", new ContactModel());
		}else {
			ContactModel contactModel = contactService.encontrarContactoPorIdentificadorRetornarContactModel(idContacto);
			model.addAttribute("contactModel", contactModel);
		}

		return ViewConstant.PAGINA_CONTACT_FORM;
	}
	
	/**
	 * Adicionar contacto.
	 *
	 * @param model the model
	 * @param contactModel the contact model
	 * @return the string
	 */
	@PostMapping("/adicionarModificarContacto")
	public String adicionarContacto(Model model, @ModelAttribute(name="contactModel") ContactModel contactModel) {
		model.addAttribute("contactModel",new ContactModel());
		model.addAttribute("result",OPERACION_NO_REALIZADA);
		ContactModel contactModelCreado = contactService.adicionarModelo(contactModel);
		LOGGER.info("SE HA CREADO/MODIFICADO EL CONTACTO: "+contactModelCreado);
		if(Objects.nonNull(contactModelCreado)) {
			model.addAttribute("result",contactModelCreado.getId());
		}
		return buscarListaContactos(model);
	}

	/**
	 * Eliminar contacto.
	 *
	 * @param model the model
	 * @param idContacto the id contacto
	 * @return the string
	 */
	@GetMapping("/eliminarContacto")
	public String eliminarContacto(Model model, @ModelAttribute(name="idContacto") Integer idContacto) {
		ContactModel eliminarContacto = contactService.eliminarContacto(idContacto);
		model.addAttribute("result",OPERACION_NO_REALIZADA);
		if(Objects.nonNull(eliminarContacto)) {
			model.addAttribute("result",eliminarContacto.getId());
		}
		return buscarListaContactos(model);
	}
	
	/**
	 * Buscar lista contactos.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/showContacts")
	public String buscarListaContactos(Model model) {
		/*obtener el usuario autenticado*/
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("userName",user.getUsername());
		
		model.addAttribute("listaContactos",contactService.consultarListaContactos());
		return ViewConstant.PAGINA_CONTACTS;
	}	
}
