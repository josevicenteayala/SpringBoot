package co.com.intergrupo.maven.boot.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.intergrupo.maven.boot.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Serializable> {

}
