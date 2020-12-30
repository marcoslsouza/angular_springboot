package com.github.marcoslsouza.vendas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	// Configuracoes das apis. Permissoes de roles (Ex. Em SecurityConfig, temos o usuario fulano com a role user)
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// hasAnyRole, varias roles.
		// permitAll, para cadastrar usuarios não precisa estar autenticado.
		//http.authorizeRequests().antMatchers("/sistema-vendas/api/cliente/**").hasRole("USER").antMatchers("/sistema-vendas/api/usuarios").permitAll();
		
		http.authorizeRequests()
				//.antMatchers("/usuarios").permitAll()
				//.anyRequest().authenticated();
				
				.antMatchers("/usuarios").permitAll()
				.antMatchers("/cliente/**", "/servicos-prestados/**").authenticated()
				.anyRequest().denyAll(); // .anyRequest().denyAll() negar todas as outras requisicoes.
	}
}
