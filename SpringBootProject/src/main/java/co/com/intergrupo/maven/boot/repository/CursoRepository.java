package co.com.intergrupo.maven.boot.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.intergrupo.maven.boot.entity.CursoEntity;

@Repository("cursoRepository")
public interface CursoRepository extends JpaRepository<CursoEntity, Serializable> {

	public List<CursoEntity> findByPrecio(int precio);
	
	public List<CursoEntity> findByPrecioAndNombre(int precio, String nombre);
	
	public List<CursoEntity> findByNombreOrderByHoras(String nombre);
	
	public List<CursoEntity> findByNombreOrPrecio(String nombre, int precio);
	
}
