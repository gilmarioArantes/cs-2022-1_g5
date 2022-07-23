package com.biblioteca.apirest.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "editoras")
public class Editora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
