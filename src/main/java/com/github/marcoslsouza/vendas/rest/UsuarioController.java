package com.github.marcoslsouza.vendas.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.marcoslsouza.vendas.model.entity.Usuario;
import com.github.marcoslsouza.vendas.model.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private final UsuarioRepository repository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED) // 201
	public void salvar(@RequestBody @Valid Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		repository.save(usuario);
	}
	
	@GetMapping
	public List<Usuario> obterTodos() {
		return repository.findAll();
	}
}
