package co.com.intergrupo.maven.boot.service;

import java.util.List;

import co.com.intergrupo.maven.boot.dto.ClienteDto;

public interface ClienteService {

	List<ClienteDto> consultarListaCompletaClientes();

	List<ClienteDto> consultaClientesNacidosAntesDeHoy();

}
