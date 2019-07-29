package com.mballen.curso.boot.controllers.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToInteger implements Converter<String, Integer> {

	@Override
	public Integer convert(String textNum) {

		// remove espaços em branco
		textNum = textNum.trim();
		if (textNum.matches("[0-9]+")) {
			return Integer.valueOf(textNum);
		}
		return null;
	}

}
