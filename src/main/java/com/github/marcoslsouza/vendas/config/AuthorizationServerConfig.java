package com.github.marcoslsouza.vendas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	// Bean em SecurityConfig
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// De onde vao vir os tokens.
	@Bean
	public TokenStore tokenStore() {
		// Gerar tokens em memoria.
		return new InMemoryTokenStore();
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// tokenStore() método acima.
		// authenticationManager criado em SecurityConfig
		endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
	}
	
	// Aplicacoes clientes. POdemos configurar as aplicacoes clientes tanto em banco de dados, quanto em memoria.
	// Aqui configuramos Cliente-id, Client-secret
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// scopes("read", "write") o client pode ler e escrever na api.
		// .authorizedGrantTypes("password") = grant_type.
		// .accessTokenValiditySeconds(60 * 30) o access token vai durar 60 segundos * 30 minutos, ou meia hora.
		clients.inMemory().withClient("my-angular-app")
			.secret("@321").scopes("read", "write").authorizedGrantTypes("password").accessTokenValiditySeconds(60 * 30);
	}
}
