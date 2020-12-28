package com.github.marcoslsouza.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.marcoslsouza.vendas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
