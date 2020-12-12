package com.github.marcoslsouza.vendas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.marcoslsouza.vendas.model.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}
