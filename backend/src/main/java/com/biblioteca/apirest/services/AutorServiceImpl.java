package com.biblioteca.apirest.services;

import com.biblioteca.apirest.models.Autor;
import com.biblioteca.apirest.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Autor find(long id) {
        return autorRepository.findById(id);
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public void delete(long id) {
        Autor autor = autorRepository.findById(id);
        autorRepository.delete(autor);
    }

    @Override
    public long count() {
        return autorRepository.count();
    }
}
