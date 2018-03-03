package com.application.boot.contactos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.boot.contactos.entity.ContactEntity;

public interface ContactRepository extends JpaRepository<ContactEntity, Serializable> {

}
