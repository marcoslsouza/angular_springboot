package com.github.marcoslsouza.vendas.rest;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiErros {
	
	@Getter
	private List<String> errors;
	
	public ApiErros(List<String> errors) {
		this.errors = errors;
	}
	
	public ApiErros(String messages) {
		this.errors = Arrays.asList(messages);
	}
}
