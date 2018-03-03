package com.application.boot.contactos.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.boot.contactos.ModelMapperContact;
import com.application.boot.contactos.entity.ContactEntity;
import com.application.boot.contactos.model.ContactModel;
import com.application.boot.contactos.repository.ContactRepository;
import com.application.boot.contactos.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private ModelMapperContact modelMapperContact;
	
	@Override
	public ContactModel adicionarModelo(ContactModel contactModel) {
		ContactEntity contactEntity = modelMapperContact.convertirContactModelAContactEntity(contactModel);
		ContactEntity contactEntityCreado = contactRepository.save(contactEntity);
		return modelMapperContact.convertirContactEntityAContactModel(contactEntityCreado);
	}
	
	@Override
	public List<ContactModel> consultarListaContactos(){
		List<ContactEntity> listaContactEntity = contactRepository.findAll();
		return modelMapperContact.crearListaContactEntityAListaContactModel(listaContactEntity);
	}
	
	@Override
	public ContactModel eliminarContacto(int idContacto) {
		Optional<ContactEntity> contactEntity = contactRepository.findById(idContacto);
		if(contactEntity.isPresent()) {
			ContactEntity contactEntityABorrar = contactEntity.get();
			contactRepository.delete(contactEntityABorrar);
			return modelMapperContact.convertirContactEntityAContactModel(contactEntityABorrar);	
		}
		return new ContactModel();
	}
	
}
