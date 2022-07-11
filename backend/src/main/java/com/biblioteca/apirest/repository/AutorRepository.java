package com.biblioteca.apirest.repository;

import com.biblioteca.apirest.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findById(long id);


}
