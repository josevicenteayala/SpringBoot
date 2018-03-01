package co.com.intergrupo.maven.boot;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import co.com.intergrupo.maven.boot.dto.CursoDto;
import co.com.intergrupo.maven.boot.entity.CursoEntity;

@Service
public class ModelMapperCurso {

	private ModelMapper modelMapper = new ModelMapper();
	
	public List<CursoDto> crearListCursoDtoDesdeListaCursoEntity(List<CursoEntity> listaCursoEntity){
		Objects.requireNonNull(listaCursoEntity);
		Type listaCursoDtoResponse = new TypeToken<List<CursoDto>>(){}.getType();
		return modelMapper.map(listaCursoEntity, listaCursoDtoResponse);
	}
	
	public CursoDto crearCursoDtoDesdeCursoEntity(CursoEntity cursoEntity) {
		return modelMapper.map(cursoEntity, CursoDto.class);
	}
	
	public CursoEntity crearCursoEntiryDesdeCursoDto(CursoDto cursoDto) {
		return modelMapper.map(cursoDto, CursoEntity.class);
	}
	
	
}
