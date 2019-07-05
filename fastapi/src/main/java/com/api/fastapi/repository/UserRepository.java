/**
 * 
 */
package com.api.fastapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.api.fastapi.model.User;

/**
 * @author vin00
 *
 */
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Long>{

}
