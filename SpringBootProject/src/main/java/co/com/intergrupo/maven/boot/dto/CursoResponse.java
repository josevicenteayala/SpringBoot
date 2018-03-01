package co.com.intergrupo.maven.boot.dto;

import java.util.List;

public class CursoResponse {

	private CursoDto cursoCreadoActualizado;
	private Integer identificadorCursoBorrado;
	
	private List<CursoDto> listaCursos;

	public List<CursoDto> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<CursoDto> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public CursoDto getCursoCreadoActualizado() {
		return cursoCreadoActualizado;
	}

	public void setCursoCreadoActualizado(CursoDto cursoCreadoActualizado) {
		this.cursoCreadoActualizado = cursoCreadoActualizado;
	}

	public Integer getIdentificadorCursoBorrado() {
		return identificadorCursoBorrado;
	}

	public void setIdentificadorCursoBorrado(Integer identificadorCursoBorrado) {
		this.identificadorCursoBorrado = identificadorCursoBorrado;
	}
	
}
