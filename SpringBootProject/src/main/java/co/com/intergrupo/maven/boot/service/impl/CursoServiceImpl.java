package co.com.intergrupo.maven.boot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.com.intergrupo.maven.boot.ModelMapperCurso;
import co.com.intergrupo.maven.boot.dto.CursoDto;
import co.com.intergrupo.maven.boot.entity.CursoEntity;
import co.com.intergrupo.maven.boot.repository.CursoRepository;
import co.com.intergrupo.maven.boot.service.CursoService;

@Service
@Qualifier("cursoServiceImpl")
public class CursoServiceImpl  implements CursoService{

	@Autowired
	@Qualifier("cursoRepository")
	private CursoRepository cursoRepository;
	
	@Autowired ModelMapperCurso modelMapperCurso;
	
	@Override
	public List<CursoDto> listaCursosPorPrecio(int precio) {
		List<CursoEntity> listaCursos = cursoRepository.findByPrecio(precio);
		return modelMapperCurso.crearListCursoDtoDesdeListaCursoEntity(listaCursos);
	}

	@Override
	public List<CursoDto> listaTodosLosCursos() {
		List<CursoEntity> listaCursos = cursoRepository.findAll();
		return modelMapperCurso.crearListCursoDtoDesdeListaCursoEntity(listaCursos);
	}

	@Override
	public CursoDto crearCurso(CursoDto cursoDto) {
		CursoEntity cursoEntity = modelMapperCurso.crearCursoEntiryDesdeCursoDto(cursoDto);
		CursoEntity cursoEntityCreado = cursoRepository.save(cursoEntity);
		return modelMapperCurso.crearCursoDtoDesdeCursoEntity(cursoEntityCreado);
	}

	@Override
	public int removerCurso(CursoDto cursoDto) {
		CursoEntity cursoEntity = modelMapperCurso.crearCursoEntiryDesdeCursoDto(cursoDto);
		cursoRepository.delete(cursoEntity);
		return cursoDto.getId();
	}

	@Override
	public CursoDto actualizarCurso(CursoDto cursoDto) {
		CursoEntity cursoEntity = modelMapperCurso.crearCursoEntiryDesdeCursoDto(cursoDto);
		CursoEntity cursoEntityCreado = cursoRepository.save(cursoEntity);
		return modelMapperCurso.crearCursoDtoDesdeCursoEntity(cursoEntityCreado);
	}

}
