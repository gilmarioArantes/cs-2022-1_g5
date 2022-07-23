package com.biblioteca.apirest.services;

import com.biblioteca.apirest.models.Autor;
import com.biblioteca.apirest.payload.response.MessageResponse;
import com.biblioteca.apirest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public ResponseEntity<?> save(Autor autor) {
        if (autorRepository.existsByName(autor.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: Já existe um autor com esse nome"));
        }else{
            return ResponseEntity.ok(autorRepository.save(autor));
        }
    }

    @Override
    public ResponseEntity<?> find(long id) {

        if (!autorRepository.existsById(id)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Erro: Não existe autor com esse id"));
        }else {
            return ResponseEntity.ok(autorRepository.findById(id));
        }
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public void delete(long id) {
        if (autorRepository.existsById(id)) {
            Autor autor = autorRepository.findById(id);
            autorRepository.delete(autor);
        }

    }

    @Override
    public long count() {

        return autorRepository.count();
    }
}
