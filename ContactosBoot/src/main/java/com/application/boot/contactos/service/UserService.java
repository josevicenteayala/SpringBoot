package com.application.boot.contactos.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.boot.contactos.entity.UserEntity;
import com.application.boot.contactos.entity.UserRoleEntity;
import com.application.boot.contactos.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService{
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 * Encargado de llamar la UserRepository y buscar un usuario por nombre
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(userName);
		Objects.requireNonNull(userEntity);
		List<GrantedAuthority> listAuthorities = buildAuthorities(userEntity.getUserRole());
		return buildUser(userEntity, listAuthorities);
	}
	
	private User buildUser(UserEntity user, List<GrantedAuthority> listaGrantedAuthirity) {
		boolean accounNotExpired = true;
		boolean accountNotLocked = true;
		boolean credentialsNotExpired = true;
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(),accounNotExpired,credentialsNotExpired,accountNotLocked,listaGrantedAuthirity);
	}
	
	private List<GrantedAuthority> buildAuthorities(Set<UserRoleEntity> userRole){
		Set<GrantedAuthority> setGrantedAuthority = new HashSet<>();
		
		Iterator<UserRoleEntity> iterator = userRole.iterator();
		while(iterator.hasNext()) {
			UserRoleEntity userRoleEntity = iterator.next();
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userRoleEntity.getRole());
			setGrantedAuthority.add(simpleGrantedAuthority);
		}
		return new ArrayList<>(setGrantedAuthority);	
	}
}
