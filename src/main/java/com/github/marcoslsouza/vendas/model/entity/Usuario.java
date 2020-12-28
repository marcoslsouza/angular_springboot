package com.github.marcoslsouza.vendas.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// unique username unico na tabela de usuarios
	@Column(unique = true, name = "login")
	private String username;
	
	@Column(name = "senha")
	private String password;
}
