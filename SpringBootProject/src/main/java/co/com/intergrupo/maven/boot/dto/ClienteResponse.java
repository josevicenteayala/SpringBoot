package co.com.intergrupo.maven.boot.dto;

import java.util.List;

public class ClienteResponse {

	List<ClienteDto> listaClientes;

	public ClienteResponse() {
		//constructor vacio
	}

	public ClienteResponse(List<ClienteDto> listaClientes) {
		super();
		this.listaClientes = listaClientes;
	}

	public List<ClienteDto> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<ClienteDto> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	@Override
	public String toString() {
		return "ClienteResponse [" + (listaClientes != null ? "listaCLientes=" + listaClientes : "") + "]";
	}
}
