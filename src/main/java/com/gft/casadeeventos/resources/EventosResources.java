package com.gft.casadeeventos.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.casadeeventos.model.Evento;
import com.gft.casadeeventos.services.EventosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Eventos")
@RestController
@RequestMapping("/api/eventos")
public class EventosResources {

	@Autowired
	private EventosService eventoServ;
	
	@ApiOperation(value="Lista de eventos.")
	@RequestMapping(method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Evento>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listar());
	}

	@ApiOperation(value="Salvar novos eventos.")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar (@ApiParam(name="Adicionar uma casa.", value="Adicionar uma casa a lista") @Valid @RequestBody Evento evento){
		evento = eventoServ.salvar(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{codigo}").buildAndExpand(evento.getCodigo()).toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Buscar um evento específico.")
	@RequestMapping(value="/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Evento>> buscar(@ApiParam(value = "Buscar um evento", example = "1")@PathVariable("codigo") Long codigo){
		Optional<Evento> evento = eventoServ.buscar(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(evento);
	}
	
	@ApiOperation(value = "Deletar um evento")
	@RequestMapping(value ="/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar (@ApiParam(value="Deletar um evento da lista.")@PathVariable("codigo") Long codigo) {
		eventoServ.deletar(codigo);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiOperation(value="Atualizar um dado do evento.")
	@RequestMapping(value="/{codigo}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(name="Atualizar um evento.", 
	value="Atualizar dados de um evento.",
	example ="1") @RequestBody Evento evento, @PathVariable("codigo") Long codigo) {
		evento.setCodigo(codigo);
		eventoServ.atualizar(evento);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiOperation(value="Buscar em ordem alfabética ascendente")
	@GetMapping("/nome/asc")
	public ResponseEntity<List<Evento>> nomeAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarNomeAsc());
	}
	
	@ApiOperation(value="Buscar em ordem alfabética descendente.")
	@GetMapping("/nome/desc")
	public ResponseEntity<List<Evento>> nomeDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarNmeDesc());
	}
	
	@ApiOperation(value="Buscar em ordem preço crescente.")
	@GetMapping("/preco/asc")
	public ResponseEntity<List<Evento>> precoAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarPrecoAsc());
	}
	
	@ApiOperation(value="Buscar em ordem preço decrescente.")
	@GetMapping("/preco/desc")
	public ResponseEntity<List<Evento>> precoDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarPrecoDesc());
	}
	
	@ApiOperation(value="Buscar em ordem capacidade crescente.")
	@GetMapping("/capacidade/asc")
	public ResponseEntity<List<Evento>> capacidadeAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarCapacidadeAsc());
	}
	
	@ApiOperation(value="Buscar em ordem capacidade decrescente.")
	@GetMapping("/capacidade/desc")
	public ResponseEntity<List<Evento>> capacidadeDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarCapacidadeDesc());
	}
	
	@ApiOperation(value="Buscar em ordem de data mais recente.")
	@GetMapping("/data/asc")
	public ResponseEntity<List<Evento>> dataAsc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarDataAsc());
	}
	
	@ApiOperation(value="Buscar em ordem de datas futuras.")
	@GetMapping("/data/desc")
	public ResponseEntity<List<Evento>> dataDesc(){
		return ResponseEntity.status(HttpStatus.OK).body(eventoServ.listarDataDesc());
	}
	
	
}

