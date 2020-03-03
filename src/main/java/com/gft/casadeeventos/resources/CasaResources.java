package com.gft.casadeeventos.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.gft.casadeeventos.model.Casadeshow;
import com.gft.casadeeventos.services.CasaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Casas")
@RestController
@RequestMapping("/api/casas")
public class CasaResources {
	
	@Autowired
	private CasaService casaServ;
	
	@ApiOperation(value="Lista das casas.")
	@RequestMapping(method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Casadeshow>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(casaServ.listar());
	}

	@ApiOperation(value="Salvar novas casas.")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar (@ApiParam(name="Adicionar uma casa.", value="Adicionar uma casa a lista") @Valid @RequestBody Casadeshow casa){
		casa = casaServ.salvar(casa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{ID}").buildAndExpand(casa.getID()).toUri();
	
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Buscar um casa específica.")
	@RequestMapping(value="/{ID}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Casadeshow>> buscar(@ApiParam(value = "Buscar uma casa", example = "1")@PathVariable("ID") Long ID){
		Optional<Casadeshow> casa = casaServ.buscar(ID);
		return ResponseEntity.status(HttpStatus.OK).body(casa);
	}
	
	@ApiOperation(value = "Deletar um casa")
	@RequestMapping(value ="/{ID}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar (@ApiParam(name="Deletar uma casa.", value="Deletar uma casa da lista.")@PathVariable("ID") Long ID) {
		casaServ.deletar(ID);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@ApiOperation(value="Atualizar um dado de uma casa.")
	@RequestMapping(value="/{ID}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(name="Atualizar uma casa.", 
	value="Atualizar dados de uma casa.",
	example ="1") @RequestBody Casadeshow casa, @PathVariable("ID") Long ID) {
		casa.setID(ID);
		casaServ.atualizar(casa);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
}

