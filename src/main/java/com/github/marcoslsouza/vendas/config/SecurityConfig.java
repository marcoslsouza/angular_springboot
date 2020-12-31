package com.github.marcoslsouza.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.marcoslsouza.vendas.service.UsuarioService;

@EnableWebSecurity
// As anotations abaixo, geram automaticamente as credenciais
//@EnableResourceServer
//@EnableAuthorizationServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioService usuarioService;

	// AuthenticationManagerBuilder atraves dele sabemos quem são os usuarios e suas senhas.
	// Constroi o gerenciamento de ususario.
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Configuração o usuario em memoria (Teste)
		//auth.inMemoryAuthentication().withUser("fulano").password("123").roles("USER");
		
		// Configurando o usuario no banco.
		// userDetailsService() carrega os dados do usuario no banco de dados.
		// passwordEncoder compara a senha recebida com a senha do usuario no banco de dados.
		// A classe UsuarioService está implementando UserDetailsService.
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}
	
	// Faz o gerenciamento de usuarios
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	// com o objeto http, podemos autorizar urls, cors e etc.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// csrf vem habilitado por padrao, e usado em aplicacoes web. Mas como a aplicacao e separada e vamos utilizar o auth então não 
		// tem necessidade. Nao se utiliza em api.
		
		// STATELESS aplicacao nao armazena sessao ela utiliza token
		http.csrf().disable().cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	// Para criptografar e decriptografar as senhas
	@Bean
	public PasswordEncoder passwordEncoder() {
		// A instrucao abaixo nao criptografa a senha, e apenas teste.
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}
}
