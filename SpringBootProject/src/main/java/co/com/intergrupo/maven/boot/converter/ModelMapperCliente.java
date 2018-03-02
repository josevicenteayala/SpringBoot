package co.com.intergrupo.maven.boot.converter;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import co.com.intergrupo.maven.boot.dto.ClienteDto;
import co.com.intergrupo.maven.boot.entity.ClienteEntity;

@Component("modelMapperCliente")
public class ModelMapperCliente {

	ModelMapper modelMapper = new ModelMapper();
	
	public List<ClienteDto> crearListaClienteDtoDesdeListaClienteEntity(List<ClienteEntity> listaClienteEntity){
		Objects.requireNonNull(listaClienteEntity, "La lista de ClienteEntity debe estar llena");
		Type tipoLista = new TypeToken<List<ClienteDto>>(){}.getType();
		
		return modelMapper.map(listaClienteEntity, tipoLista);
	}
	
	public ClienteDto crearClienteDtoDesdeClienteEntity(ClienteEntity clienteEntity) {
		return modelMapper.map(clienteEntity, ClienteDto.class);
	}
	
	public ClienteEntity crearClienteEntityDesdeClienteDto(ClienteDto clienteDto) {
		return modelMapper.map(clienteDto, ClienteEntity.class);
	}
}
