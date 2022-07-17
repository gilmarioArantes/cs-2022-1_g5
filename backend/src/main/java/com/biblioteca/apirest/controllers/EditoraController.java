package com.example.demo.editora;

import java.util.List;
import java.util.Optional;

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

@RestController
@RequestMapping(value = "/editoras", path = "/editoras")
public class EditoraController {
	
	@Autowired
	private EditoraRepository repository;
	
	@Autowired
	private EditoraService service;
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Editora editora) {

	editora = service.salvar(editora);

	return ResponseEntity.status(HttpStatus.CREATED)
	.body(editora);

	}
	
	@GetMapping
	public List<Editora> listar() {
	return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> buscar(@PathVariable Long id) {
	Optional<Editora> editora = repository.findById(id);

	if (editora.isPresent()) {
	return ResponseEntity.ok(editora.get());
	}

	return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Editora> atualizar(@PathVariable Long id, @RequestBody Editora editora) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		editora.setId(id);
		editora = repository.save(editora);
		return ResponseEntity.ok(editora);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> romover(@PathVariable Long id) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}