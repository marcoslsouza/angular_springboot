package com.github.marcoslsouza.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.marcoslsouza.vendas.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
