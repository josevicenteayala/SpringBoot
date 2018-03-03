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

import com.application.boot.contactos.constant.ViewConstant;
import com.application.boot.contactos.model.ContactModel;
import com.application.boot.contactos.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private ContactService contactService;
	
	@GetMapping("/cancel")
	public String cancel(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return ViewConstant.VISTA_CONTACT_FORM;
	}

	@GetMapping("/contactForm")
	public String redireccionAContactForm(Model model) {
		model.addAttribute("contactModel", new ContactModel());
		return ViewConstant.VISTA_CONTACT_FORM;
	}
	
	@PostMapping("/adicionarContacto")
	public String adicionarContacto(Model model, @ModelAttribute(name="contactModel") ContactModel contactModel) {
		model.addAttribute("contactModel",new ContactModel());
		ContactModel contactModelCreado = contactService.adicionarModelo(contactModel);
		LOGGER.info("SE HA CREADO EL CONTACTO: "+contactModelCreado);
		if(Objects.nonNull(contactModelCreado)) {
			model.addAttribute("result",contactModelCreado.getId());
		}
		model.addAttribute("listaContactos",contactService.consultarListaContactos());
		return ViewConstant.PAGINA_CONTACTS;
	}
	
	@GetMapping("/eliminarContacto")
	public String eliminarContacto(Model model, @ModelAttribute(name="idContacto") Integer idContacto) {
		ContactModel eliminarContacto = contactService.eliminarContacto(idContacto);
		model.addAttribute("listaContactos",contactService.consultarListaContactos());
		return ViewConstant.PAGINA_CONTACTS;
	}
	
}
