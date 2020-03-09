package com.gft.casadeeventos.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.casadeeventos.model.Usuario;
import com.gft.casadeeventos.services.UsuariosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosResource {
	
	@Autowired
	private UsuariosService usuServ;
	
	@ApiOperation(value="Listar usuarios.")
	@GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<Usuario>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(usuServ.listarUsu());
	}
	
	@ApiOperation(value="Buscar um usuario específico.")
	@GetMapping(value="/{ID}")
	public ResponseEntity<Optional<Usuario>> buscar(@ApiParam(value = "Buscar uma casa", example = "1")@PathVariable("ID") Long id){
		Optional<Usuario> usu = usuServ.buscarUsuario(id);
		return ResponseEntity.status(HttpStatus.OK).body(usuServ.buscarUsuario(id));
	}
	
	@ApiOperation(value = "Deletar um usuário.")
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar (@ApiParam(value="Deletar um usuário da lista.")
											@PathVariable("id") Long id) {
		usuServ.deletar(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

}
