package com.gft.casadeeventos.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Evento> buscar(@ApiParam(value = "Buscar um evento", example = "1")@PathVariable("codigo") Long codigo){
		Evento evento = eventoServ.buscar(codigo);
		return ResponseEntity.status(HttpStatus.OK).body(evento);
	}
	
	@ApiOperation(value = "Deletar um evento")
	@RequestMapping(value ="/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar (@ApiParam(name="Deletar um evento.", value="Deletar um evento da lista.")@PathVariable("codigo") Long codigo) {
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
	
	
	
}

