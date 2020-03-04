package com.gft.casadeeventos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gft.casadeeventos.model.Evento;
import com.gft.casadeeventos.repository.Casadeshows;
import com.gft.casadeeventos.repository.Eventos;
import com.gft.casadeeventos.services.exceptions.CasaNaoEncontradaException;
import com.gft.casadeeventos.services.exceptions.EventoExistenteException;
import com.gft.casadeeventos.services.exceptions.EventoNaoEncontradoException;

@Service
public class EventosService {

		@Autowired
		private Eventos eventoRepo;
		
		@Autowired
		private Casadeshows casaRepo;

		public List<Evento> listar() {
			return eventoRepo.findAll();
		}

		public Evento salvar(Evento evento) {
			if (evento.getCodigo()!= null) {
				Optional<Evento> a = eventoRepo.findById(evento.getCodigo());
				if (a.isPresent()) {
					throw new EventoExistenteException("Evento já existe!");
				}
			}
			return eventoRepo.save(evento);
		}

		public Evento buscar(Long codigo) {
			
			Evento evento = eventoRepo.findById(codigo).get();

			if (evento == null) {
				throw new EventoNaoEncontradoException("O evento não pode ser encontrado!");
			}
			return evento;
		}

		public void deletar(Long codigo) {
			try {
				eventoRepo.deleteById(codigo);
			} catch (EmptyResultDataAccessException e) {
				throw new CasaNaoEncontradaException("A casa não foi encontrada!");
			}
		}

		public void atualizar(Evento evento) {
			verificarExistencia(evento);
			eventoRepo.save(evento);
		}

		private void verificarExistencia(Evento evento) {
			buscar(evento.getCodigo());
		}

		public List<Evento> listarNomeAsc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.ASC,"nome"));
		}
		
		public List<Evento> listarNmeDesc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.DESC,"nome"));
		}
		
		public List<Evento> listarCapacidadeAsc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.ASC,"nome"));
		}
		
		public List<Evento> listarCapacidadeDesc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.DESC,"nome"));
		}
		
		public List<Evento> listarDataAsc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.ASC,"data"));
		}
		
		public List<Evento> listarDataDesc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.DESC,"data"));
		}
		
		public List<Evento> listarPrecoAsc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.ASC,"preco"));
		}
		
		public List<Evento> listarPrecoDesc() {
			return eventoRepo.findAll(Sort.by(Sort.Direction.DESC,"preco"));
		}
		
}

