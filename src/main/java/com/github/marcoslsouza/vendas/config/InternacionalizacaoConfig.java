package com.github.marcoslsouza.vendas.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternacionalizacaoConfig {
	
	// Indica a localizacao do arquivo de mensagens de validacao
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages"); // Arquivo em resources "messages.properties"
		messageSource.setDefaultEncoding("ISO-8859-1"); // Encode para apresentacao das mensagens
		messageSource.setDefaultLocale(Locale.getDefault()); // Detecta o Locale do SO
		return messageSource;
	}
	
	// Faz a interpolacao entre as mensagens e as regras de validacao na classe de modelo
	@Bean
	public LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
}
