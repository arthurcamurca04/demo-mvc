package com.mballen.curso.boot.controllers.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballen.curso.boot.modelos.Departamento;
import com.mballen.curso.boot.repositorios.RepositorioDepartamento;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento>{

	@Autowired
	private RepositorioDepartamento depRepository;
	
	@Override
	public Departamento convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		
		Long id = Long.parseLong(text);
		return depRepository.getOne(id);
	}

}
