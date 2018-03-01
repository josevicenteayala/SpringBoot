package co.com.intergrupo.maven.boot.converter;

import org.springframework.stereotype.Component;

import co.com.intergrupo.maven.boot.ModelMapperCurso;
import co.com.intergrupo.maven.boot.dto.CursoDto;
import co.com.intergrupo.maven.boot.entity.CursoEntity;

@Component("cursoConverter")
public class CursoConverter {

	private ModelMapperCurso modelMapperCurso = new ModelMapperCurso();
	
	/*transformar de Entity a dto*/
	public CursoDto cursoEntidadACursoDto(CursoEntity cursoEntity) {
		return modelMapperCurso.crearCursoDtoDesdeCursoEntity(cursoEntity);
	}
	

	/*Tranformar dto a Entity*/
	
	public CursoEntity cursoDtoACursoEntity(CursoDto cursoDto) {
		return modelMapperCurso.crearCursoEntiryDesdeCursoDto(cursoDto);
	}
	
	
}
