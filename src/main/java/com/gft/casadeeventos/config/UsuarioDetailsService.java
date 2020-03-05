package com.gft.casadeeventos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gft.casadeeventos.model.Admim;
import com.gft.casadeeventos.model.UserPrincipal;
import com.gft.casadeeventos.model.Usuario;
import com.gft.casadeeventos.repository.Usuarios;




@Service
public class UsuarioDetailsService implements UserDetailsService {
	
	@Autowired
	private Usuarios repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		
		
		
		Usuario usu = repo.findByUsername(username);
		if(usu==null) {
			throw new UsernameNotFoundException("Usuario nao encontrado");
		}
		if(usu.isAdm()==true){
			return new Admim(usu);
		}
			else {
				return new UserPrincipal(usu
						);
			}
	}

}