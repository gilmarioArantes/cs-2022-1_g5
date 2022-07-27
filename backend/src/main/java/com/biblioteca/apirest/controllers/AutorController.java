package com.biblioteca.apirest.controllers;

import com.biblioteca.apirest.models.Autor;
import com.biblioteca.apirest.services.AutorService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or hasRole('USER')")
    @ApiOperation(value="Mostra lista de autores")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @GetMapping("/autores")
    public List<Autor> listarAutores(){

        return autorService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN') or hasRole('USER')")
    @ApiOperation(value="Mostra autor específico")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @GetMapping("/autor/{id}")
    public ResponseEntity<?> autorUnico(@PathVariable(value = "id") long id){

        return autorService.find(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @ApiOperation(value="Cadastra um novo autor")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @PostMapping("/autor")
    public ResponseEntity<?> cadastrarAutor(@RequestBody Autor autor){

        return autorService.save(autor);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @ApiOperation(value="Edita autor")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @PutMapping("/autor")
    public ResponseEntity<?> editarAutor(@RequestBody Autor autor){

        return autorService.save(autor);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('LIBRARIAN')")
    @ApiOperation(value="Deleta autor")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", example = "Bearer access_token")
    @DeleteMapping("/autor/{id}")
    public ResponseEntity<?> deletarAutor(@PathVariable(value="id") long id) {

        return autorService.delete(id);
    }
}
