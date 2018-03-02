package co.com.intergrupo.maven.boot.controller.curso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.intergrupo.maven.boot.dto.ClienteDto;
import co.com.intergrupo.maven.boot.dto.ClienteResponse;
import co.com.intergrupo.maven.boot.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value="/consultaListaClientes", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ClienteResponse consultarListaCompletaDeClientes() {
		List<ClienteDto> listaClientes = clienteService.consultarListaCompletaClientes();
		ClienteResponse clienteResponse = new ClienteResponse();
		clienteResponse.setListaClientes(listaClientes);
		return clienteResponse;
	}
	
	@GetMapping(value="/consultaClientesNacidosAntesDeHoy", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ClienteResponse consultaClientesNacidosAntesDeHoy() {
		List<ClienteDto> listaClientes = clienteService.consultaClientesNacidosAntesDeHoy();
		ClienteResponse clienteResponse = new ClienteResponse();
		clienteResponse.setListaClientes(listaClientes);
		return clienteResponse;
	}
	
}
