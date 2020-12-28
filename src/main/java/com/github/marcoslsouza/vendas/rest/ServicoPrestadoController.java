package com.github.marcoslsouza.vendas.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.marcoslsouza.vendas.BigDecimalConverter;
import com.github.marcoslsouza.vendas.model.entity.Cliente;
import com.github.marcoslsouza.vendas.model.entity.ServicoPrestado;
import com.github.marcoslsouza.vendas.model.repository.ClienteRepository;
import com.github.marcoslsouza.vendas.model.repository.ServicoPrestadoRepository;
import com.github.marcoslsouza.vendas.rest.dto.ServicoPrestadoDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // Cria um construtor com os arqumentos marcados com final
@RequestMapping("/servicos-prestados")
//@CrossOrigin("http://localhost:4200")
public class ServicoPrestadoController {
	
	// Injecao de dependencias
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;
	
	// Cria um dto porque iremos receber o id do cliente da tela e não o objeto cliente.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Long idCliente = dto.getIdCliente();
		Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente inexistente!"));
		
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setDescricao(dto.getDescricao());
		servicoPrestado.setData(data);
		servicoPrestado.setCliente(cliente);
		// Conversor de String para BigDecimal
		servicoPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		return repository.save(servicoPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(@RequestParam(value = "nome", required=false, defaultValue = "") String nome, @RequestParam(value = "mes", required=false) Integer mes) {
		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
}
