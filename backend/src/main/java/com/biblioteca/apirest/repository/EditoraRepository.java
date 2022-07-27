package com.biblioteca.apirest.repository;

import com.biblioteca.apirest.models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {

    Editora findById(long id);

    Boolean existsByNome(String nome);

    Boolean existsById(long id);

}