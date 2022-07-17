package com.biblioteca.apirest.services;
import com.biblioteca.apirest.models.Autor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AutorService {

    ResponseEntity<?> save(Autor autor);

    ResponseEntity<?> find(long id);

    List<Autor> findAll();

    void delete(long id);

    long count();
}
