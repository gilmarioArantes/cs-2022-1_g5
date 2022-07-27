package com.biblioteca.apirest.controllers;

import com.biblioteca.apirest.models.Editora;
import com.biblioteca.apirest.repository.EditoraRepository;
import com.biblioteca.apirest.services.EditoraService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class EditoraController {
	
	@Autowired
	private EditoraService editoraService;

	@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
	@ApiOperation(value="Cadastrar uma nova editora")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PostMapping("/editora")
	public ResponseEntity<?> cadastrarEditora(@RequestBody Editora editora) {

		return editoraService.save(editora);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or hasRole('USER')")
	@ApiOperation(value="Buscar todas as editoras")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping("/editoras")
	public List<Editora> listarEditoras() {

		return editoraService.findAll();
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or hasRole('USER')")
	@ApiOperation(value="Buscar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@GetMapping("/editora/{id}")
	public ResponseEntity<?> editoraUnico(@PathVariable(value = "id") long id) {

		return editoraService.find(id);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
	@ApiOperation(value="Editar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@PutMapping("/editora/{id}")
	public ResponseEntity<?> atualizarEditora(@RequestBody Editora editora) {

		return editoraService.save(editora);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
	@ApiOperation(value="Deletar editora por id")
	@ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
	@DeleteMapping("/editora/{id}")
	public ResponseEntity<?> deletarEditora(@PathVariable(value = "id") long id) {

		return editoraService.delete(id);
	}
}