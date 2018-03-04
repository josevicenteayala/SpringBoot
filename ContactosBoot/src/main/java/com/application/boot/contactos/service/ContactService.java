package com.application.boot.contactos.service;

import java.util.List;

import com.application.boot.contactos.model.ContactModel;

public interface ContactService {

	ContactModel adicionarModelo(ContactModel contactModel);

	List<ContactModel> consultarListaContactos();

	ContactModel eliminarContacto(int idContacto);

	ContactModel encontrarContactoPorIdentificadorRetornarContactModel(int idContacto);

	ContactModel modificarModelo(ContactModel contactModel);

}
