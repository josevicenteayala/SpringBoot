package com.application.boot.contactos.service.impl;

import java.util.List;
import java.util.Objects;
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
		return adicionarModificarContactEntity(contactModel);
	}
	
	@Override
	public List<ContactModel> consultarListaContactos(){
		List<ContactEntity> listaContactEntity = contactRepository.findAll();
		return modelMapperContact.crearListaContactEntityAListaContactModel(listaContactEntity);
	}
	
	@Override
	public ContactModel eliminarContacto(int idContacto) {
		ContactEntity contactEntity;
		try {
			contactEntity = encontrarContactoPorIdentificador(idContacto);
			if(Objects.nonNull(contactEntity)) {
				contactRepository.delete(contactEntity);
				return modelMapperContact.convertirContactEntityAContactModel(contactEntity);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ContactModel();
	}
	
	@Override
	public ContactModel encontrarContactoPorIdentificadorRetornarContactModel(int idContacto){
		Optional<ContactEntity> contactEntity = contactRepository.findById(idContacto);
		if(contactEntity.isPresent()) {
			return modelMapperContact.convertirContactEntityAContactModel(contactEntity.get());
		}
		return new ContactModel();
	}
	
	@Override
	public ContactModel modificarModelo(ContactModel contactModel) {
		return adicionarModificarContactEntity(contactModel);
	}

	/**
	 * @param contactModel
	 * @return
	 */
	private ContactModel adicionarModificarContactEntity(ContactModel contactModel) {
		Objects.requireNonNull(contactModel);
		ContactEntity contactEntity = modelMapperContact.convertirContactModelAContactEntity(contactModel);
		ContactEntity contactEntityCreado = contactRepository.save(contactEntity);
		return modelMapperContact.convertirContactEntityAContactModel(contactEntityCreado);
	}
	
	private ContactEntity encontrarContactoPorIdentificador(int idContacto) throws Exception {
		Optional<ContactEntity> contactEntity = contactRepository.findById(idContacto);
		if(contactEntity.isPresent()) {
			return contactEntity.get();
		}
		throw new Exception("No se encontro la entidad solicitada");
	}

}
