package co.com.intergrupo.maven.boot.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import co.com.intergrupo.maven.boot.entity.ClienteEntity;
import co.com.intergrupo.maven.boot.entity.CursoEntity;
import co.com.intergrupo.maven.boot.entity.QClienteEntity;
import co.com.intergrupo.maven.boot.entity.QCursoEntity;

@Repository("queryDSLExampleRepo")
public class QueryDSLExampleRepo {

	private QCursoEntity qCursoEntity = QCursoEntity.cursoEntity;
	private QClienteEntity qClienteEntity = QClienteEntity.clienteEntity;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void find() {
		JPAQuery<CursoEntity> query = new JPAQuery<>(entityManager);
		CursoEntity curso = query.select(qCursoEntity).from(qCursoEntity).where(qCursoEntity.id.eq(50)).fetchOne();
		
		JPAQuery<CursoEntity> listaCursos = query.select(qCursoEntity).from(qCursoEntity).where(qCursoEntity.horas.between(10, 50));
	}
	
	public void find(boolean buscarPorFinalizacionNombreCurso) {
		BooleanBuilder booleanBuilder = new BooleanBuilder(qCursoEntity.id.eq(50));
		
		if(buscarPorFinalizacionNombreCurso) {
			Predicate predicate = qCursoEntity.nombre.endsWith("Boot");
			booleanBuilder.and(predicate);
		}else {
			Predicate predicate = qCursoEntity.nombre.endsWith("Cloud");
			booleanBuilder.or(predicate);
		}
		
		JPAQuery<CursoEntity> query = new JPAQuery<>(entityManager);
		CursoEntity curso = query.select(qCursoEntity).from(qCursoEntity).where(booleanBuilder).fetchOne();
	}
	
	public List<ClienteEntity> listaClientesNacidosAntesDeHoy(){
		try {
			JPAQuery<ClienteEntity> jpaQuery = new JPAQuery<>(entityManager);
			
			JPAQuery<ClienteEntity> listaClientes = jpaQuery.select(qClienteEntity).from(qClienteEntity).where(qClienteEntity.fechaNacimiento.before(new Date()));
			Objects.requireNonNull(listaClientes, "LISTA DE CLIENTES NO ENCONTRADA");
			return listaClientes.fetch();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
}
