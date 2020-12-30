package com.github.marcoslsouza.vendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.marcoslsouza.vendas.model.entity.Usuario;
import com.github.marcoslsouza.vendas.model.repository.UsuarioRepository;

// @Service sao classes que tem regra de negocio, mas nao acessam diretamente base de dados, nao sao repositorios
@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository repository;

	// Carrega os dados do usuario no banco
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
		// Cria uma instancia de UserDetails.
		// User converte o usuario para um UserDetails. Poderia ser assim ou colocar a classe Usuario para implementar UserDetails.
		return User.builder().username(usuario.getUsername()).password(usuario.getPassword()).roles("USER").build();
	}
	
}
