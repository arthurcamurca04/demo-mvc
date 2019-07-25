package com.mballen.curso.boot.controllers.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mballen.curso.boot.modelos.Cargo;
import com.mballen.curso.boot.repositorios.RepositorioCargo;

@Component
public class StringToCargoConverter implements Converter<String, Cargo>{

	@Autowired
	private RepositorioCargo cargo;
	
	@Override
	public Cargo convert(String text) {
		if (text.isEmpty()) {
			return null;
			
		}
		Long id = Long.parseLong(text);
		return cargo.getOne(id);
	}

}
