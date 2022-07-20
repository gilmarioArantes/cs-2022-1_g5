package com.biblioteca.apirest.controllers;


import com.biblioteca.apirest.models.Editora;
import com.biblioteca.apirest.repository.EditoraRepository;
import com.biblioteca.apirest.services.EditoraService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
public class EditoraController {
	
	@Autowired
	private EditoraRepository repository;
	
	@Autowired
	private EditoraService service;

	@ApiOperation(value="Cadastrar uma nova editora")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PostMapping("/editora")
	public ResponseEntity<?> adicionar(@RequestBody Editora editora) {

	editora = service.salvar(editora);

	return ResponseEntity.status(HttpStatus.CREATED)
	.body(editora);

	}

	@ApiOperation(value="Buscar todas as editoras")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping("/editoras")
	public List<Editora> listar() {

		return repository.findAll();
	}


	@ApiOperation(value="Buscar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping("/editora/{id}")
	public ResponseEntity<Editora> buscar(@PathVariable Long id) {
		Optional<Editora> editora = repository.findById(id);

		if (editora.isPresent()) {
			return ResponseEntity.ok(editora.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value="Editar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PutMapping("/editora/{id}")
	public ResponseEntity<Editora> atualizar(@PathVariable Long id, @RequestBody Editora editora) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		editora.setId(id);
		editora = repository.save(editora);
		return ResponseEntity.ok(editora);
	}


	@ApiOperation(value="Deletar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@DeleteMapping("/editora/{id}")
	public ResponseEntity<Void> romover(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}