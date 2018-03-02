package co.com.intergrupo.maven.boot.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.com.intergrupo.maven.boot.converter.ModelMapperCliente;
import co.com.intergrupo.maven.boot.dto.ClienteDto;
import co.com.intergrupo.maven.boot.entity.ClienteEntity;
import co.com.intergrupo.maven.boot.repository.ClienteRepository;
import co.com.intergrupo.maven.boot.repository.QueryDSLExampleRepo;
import co.com.intergrupo.maven.boot.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	@Qualifier("queryDSLExampleRepo")
	private QueryDSLExampleRepo queryDSLExampleRepo;	
	
	@Autowired
	private ModelMapperCliente modelMapperCliente;
	
	@Override
	public List<ClienteDto> consultarListaCompletaClientes(){
		List<ClienteEntity> listaClientes = clienteRepository.findAll();
		return modelMapperCliente.crearListaClienteDtoDesdeListaClienteEntity(listaClientes);
	}
	
	@Override
	public List<ClienteDto> consultaClientesNacidosAntesDeHoy(){
		LOGGER.info("Antes de llamar al query");
		List<ClienteEntity> listaClientes = queryDSLExampleRepo.listaClientesNacidosAntesDeHoy();
		listaClientes.stream().forEach(c->LOGGER.info("El cliente encontrado es: ".concat(c.toString())));
		return modelMapperCliente.crearListaClienteDtoDesdeListaClienteEntity(listaClientes);
	}
	
}
