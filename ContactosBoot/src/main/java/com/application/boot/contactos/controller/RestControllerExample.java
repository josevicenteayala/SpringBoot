package com.application.boot.contactos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.boot.contactos.model.ContactModel;

@RestController
@RequestMapping("/rest")
public class RestControllerExample {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerExample.class);
	
	/**
	 * Check rest.
	 *
	 * @return the response entity
	 */
	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest(){
		LOGGER.info("Entrando a CheckRest");
		ContactModel contactModel = new ContactModel(2,"Pedro","Madrid","4512369","Barcelona");
		LOGGER.info("Antes del hacer el retorno");
		return new ResponseEntity<ContactModel>(contactModel,HttpStatus.OK);
	}
	
}
