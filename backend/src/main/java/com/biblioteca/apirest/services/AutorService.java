package com.biblioteca.apirest.services;
import com.biblioteca.apirest.models.Autor;

import java.util.List;

public interface AutorService {

    Autor save(Autor autor);

    Autor find(long id);

    List<Autor> findAll();

    void delete(long id);

    long count();
}
