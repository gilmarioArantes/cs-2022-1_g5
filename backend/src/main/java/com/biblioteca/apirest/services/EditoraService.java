package com.biblioteca.apirest.services;

import com.biblioteca.apirest.models.Editora;
import com.biblioteca.apirest.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

	@Autowired
	private EditoraRepository repository;

	public Editora salvar(Editora editora) {
		return repository.save(editora);
	}
}
