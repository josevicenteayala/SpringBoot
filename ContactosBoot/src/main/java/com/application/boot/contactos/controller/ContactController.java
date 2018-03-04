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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.boot.contactos.constant.ViewConstant;
import com.application.boot.contactos.model.ContactModel;
import com.application.boot.contactos.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final int OPERACION_NO_REALIZADA = 0;

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return buscarListaContactos(model);
	}

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
	 * @param model
	 * @return
	 */
	private String buscarListaContactos(Model model) {
		model.addAttribute("listaContactos",contactService.consultarListaContactos());
		return ViewConstant.PAGINA_CONTACTS;
	}	
}
