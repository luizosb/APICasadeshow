package com.gft.casadeeventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.casadeeventos.model.Casadeshow;
import com.gft.casadeeventos.repository.Casadeshows;
import com.gft.casadeeventos.services.exceptions.CasaNaoEncontradaException;
import com.gft.casadeeventos.services.exceptions.EventoExistenteException;
import com.gft.casadeeventos.services.exceptions.EventoNaoEncontradoException;

@Service
public class CasaService {

	@Autowired
	private Casadeshows casaRepo;

	public List<Casadeshow> listar() {
		return casaRepo.findAll();
	}

	public Casadeshow salvar(Casadeshow casa) {
		if (casa.getID() != null) {
			Optional<Casadeshow> a = casaRepo.findById(casa.getID());
			if (a.isPresent()) {
				throw new EventoExistenteException("Autor já existe!");
			}
		}
		return casaRepo.save(casa);
	}

	public Optional<Casadeshow> buscar(Long id) {
		Optional<Casadeshow> casa = casaRepo.findById(id);

		if (casa.isEmpty()) {
			throw new EventoNaoEncontradoException("O evento não pode ser encontrado!");
		}
		return casa;
	}

	public void deletar(Long ID) {
		try {
			casaRepo.deleteById(ID);
		} catch (EmptyResultDataAccessException e) {
			throw new CasaNaoEncontradaException("A casa não foi encontrada!");
		}
	}

	public void atualizar(Casadeshow casa) {
		verificarExistencia(casa);
		casaRepo.save(casa);
	}

	private void verificarExistencia(Casadeshow casa) {
		buscar(casa.getID());
	}
	
}