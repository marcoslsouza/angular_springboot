package com.github.marcoslsouza.vendas;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class BigDecimalConverter {

	// 1.000,00 -> 1000.00 (String para BigDecimal)
	public BigDecimal converter(String value) {
		if(value == null)
			return null;
		value = value.replace(".", "").replace(",", ".");
		return new BigDecimal(value);
	}
	
}
