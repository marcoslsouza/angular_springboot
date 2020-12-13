package com.github.marcoslsouza.vendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.marcoslsouza.vendas.model.entity.Cliente;
import com.github.marcoslsouza.vendas.model.repository.ClienteRepository;

@SpringBootApplication
@RestController
public class VendasApplication {

	@GetMapping("/hello")
	public String helloworld() {
		return "hello world!";
	}
	
	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			// Na classe cliente temos a anotation @builder
			Cliente cliente = Cliente.builder().cpf("03582352005").nome("Fulano").build();
			repository.save(cliente);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
