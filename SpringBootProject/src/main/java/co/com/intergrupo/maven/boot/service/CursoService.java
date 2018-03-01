package co.com.intergrupo.maven.boot.service;

import java.util.List;

import co.com.intergrupo.maven.boot.dto.CursoDto;

public interface CursoService {

	public List<CursoDto> listaCursosPorPrecio(int precio);
	
	public List<CursoDto> listaTodosLosCursos();
	
	public CursoDto crearCurso(CursoDto cursoDto);
	
	public int removerCurso(CursoDto cursoDto);
	
	public CursoDto actualizarCurso(CursoDto cursoDto);
}
