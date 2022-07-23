package com.biblioteca.apirest.services;

import com.biblioteca.apirest.models.Editora;
import com.biblioteca.apirest.payload.response.MessageResponse;
import com.biblioteca.apirest.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository editoraRepository;

	public ResponseEntity<?> save(Editora editora){
		if (editoraRepository.existsByName(editora.getNome())){
			return ResponseEntity.badRequest().body(new MessageResponse("Erro: Já existe uma editora com esse nome"));
		}else{
			return ResponseEntity.ok(editoraRepository.save(editora));
		}
	}

	public ResponseEntity<?> find(long id){

		if (!editoraRepository.existsById(id)){
			return ResponseEntity.badRequest().body(new MessageResponse("Erro: Não existe editora com esse id"));
		}else{
			return ResponseEntity.ok(editoraRepository.findById(id));
		}
	}

	public List<Editora> findAll(){
		return editoraRepository.findAll();
	}

	public void delete(long id){
		if (editoraRepository.existsById(id)){
			Editora editora = editoraRepository.findById(id);
			editoraRepository.delete(editora);
		}
	}

	public long count(){
		return editoraRepository.count();
	}
}
