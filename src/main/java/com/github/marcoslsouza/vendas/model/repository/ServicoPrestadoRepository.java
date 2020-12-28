package com.github.marcoslsouza.vendas.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.marcoslsouza.vendas.model.entity.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

	// Atraves das annotations o JPA já sabe que é para fazer um join com clientes
	@Query("SELECT s FROM ServicoPrestado s JOIN s.cliente c WHERE UPPER(c.nome) LIKE UPPER(:nome) AND MONTH(s.data) = :mes")
	List<ServicoPrestado> findByNomeClienteAndMes(@Param("nome") String nome, @Param("mes") Integer mes);

}
