package com.gft.casadeeventos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.casadeeventos.model.Ingresso;
import com.gft.casadeeventos.repository.Ingressos;
import com.gft.casadeeventos.services.exceptions.EventoNaoEncontradoException;

@Service
public class CompraService {

	@Autowired
	private Ingressos ingressoRepo;
	
	public List<Ingresso> buscarHistorico() {
		return ingressoRepo.findAll();
	}
	
	public Ingresso buscarEspecifico(Long id) {
			Ingresso ing = ingressoRepo.findById(id).get();

			if (ing == null) {
				throw new EventoNaoEncontradoException("O evento não pode ser encontrado!");
			}
			return ing;
	}
}
