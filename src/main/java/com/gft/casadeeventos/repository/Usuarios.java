package com.gft.casadeeventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.casadeeventos.model.Usuario;

public interface Usuarios extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);


}
