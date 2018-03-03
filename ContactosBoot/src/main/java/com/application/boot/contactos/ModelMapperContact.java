package com.application.boot.contactos;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import com.application.boot.contactos.entity.ContactEntity;
import com.application.boot.contactos.model.ContactModel;

@Component
public class ModelMapperContact {

	private ModelMapper modelMapper = new ModelMapper();
	
	public ContactModel convertirContactEntityAContactModel(ContactEntity contactEntity) {
		return modelMapper.map(contactEntity, ContactModel.class);
	}
	
	public ContactEntity convertirContactModelAContactEntity(ContactModel contactModel) {
		return modelMapper.map(contactModel, ContactEntity.class);
	}
	
	public List<ContactModel> crearListaContactEntityAListaContactModel(List<ContactEntity> listaContactEntity){
		Objects.requireNonNull(listaContactEntity);
		Type listaTemporal = new TypeToken<List<ContactModel>>(){}.getType();
		return modelMapper.map(listaContactEntity, listaTemporal);
	}
}
