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
@RequestMapping(value = "/autores", path = "/autores")
public class AutorController {
	
	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private AutorService service;
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Autor autor) {

	autor = service.salvar(autor);

	return ResponseEntity.status(HttpStatus.CREATED)
	.body(autor);

	}
	
	@GetMapping
	public List<Autor> listar() {
	return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autor> buscar(@PathVariable Long id) {
	Optional<Autor> autor = repository.findById(id);

	if (autor.isPresent()) {
	return ResponseEntity.ok(autor.get());
	}

	return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor autor) {
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		autor.setId(id);
		autor = repository.save(autor);
		return ResponseEntity.ok(autor);
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

