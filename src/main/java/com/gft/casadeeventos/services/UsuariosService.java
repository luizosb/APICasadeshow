package com.gft.casadeeventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.casadeeventos.model.Usuario;
import com.gft.casadeeventos.repository.Usuarios;
import com.gft.casadeeventos.services.exceptions.CasaNaoEncontradaException;

@Service
public class UsuariosService {

	
	@Autowired
	private Usuarios usuRepo;

	public List<Usuario> listarUsu() {
		return usuRepo.findAll();
	}

	public Optional<Usuario> buscarUsuario(Long id) {
		Optional<Usuario> usu = usuRepo.findById(id);

		if (usu.isEmpty()) {
			throw new CasaNaoEncontradaException("A casa não pode ser encontrado!");
		}
		return usu;
	}

	
	
	
}

