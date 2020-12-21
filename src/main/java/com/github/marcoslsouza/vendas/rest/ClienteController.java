package com.github.marcoslsouza.vendas.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.marcoslsouza.vendas.model.entity.Cliente;
import com.github.marcoslsouza.vendas.model.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

	private final ClienteRepository repository;
	
	@Autowired
	public ClienteController(ClienteRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED) // Mapea o objeto de retorno para o corpo da resposta. // HttpStatus.CREATED retorna created ou inves de ok (200)
	public Cliente salvar(@RequestBody @Valid Cliente cliente) { // @RequestBody informa que os valores de cliente vem no corpo da requisição json.
		return repository.save(cliente);
	}
	
	@GetMapping("/{id}")
	public Cliente acharPorId(@PathVariable Long id) {
		// Caso não encontre lanca uma excecao
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // Status de sucesso que indica que não há nenhum recurso de retorno.
	public void deletar(@PathVariable Long id) {
		repository.findById(id).map(cliente -> {
			repository.delete(cliente);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualizado) {
		repository.findById(id).map(cliente -> {
			clienteAtualizado.setId(cliente.getId());
			clienteAtualizado.setDataCadastro(cliente.getDataCadastro());
			return repository.save(clienteAtualizado);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@GetMapping
	public List<Cliente> obterTodos() {
		return repository.findAll();
	}
}
