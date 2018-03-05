package com.application.boot.contactos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.boot.contactos.entity.LogEntity;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<LogEntity, Serializable> {

	
	
}
