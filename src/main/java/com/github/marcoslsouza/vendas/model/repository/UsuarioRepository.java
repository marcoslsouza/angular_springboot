package com.github.marcoslsouza.vendas.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.marcoslsouza.vendas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// Retorna um optional de usuario. Usa a propriedade username de Usuario.
	// Porque Optional porque se encontrar um objeto ele retorna, se não retorna vazio.
	Optional<Usuario> findByUsername(String username);
}
