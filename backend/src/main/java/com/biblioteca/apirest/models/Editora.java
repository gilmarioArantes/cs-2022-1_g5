package com.biblioteca.apirest.models;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;



@Data
@Entity
@Table(name = "editoras")
public class Editora implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank
	private String nome;


}
