package com.mballen.curso.boot.validator;

import java.time.LocalDate;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.mballen.curso.boot.modelos.Funcionario;

public class FuncionarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {		
		return Funcionario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object object, Errors error) {
		Funcionario func = (Funcionario) object;
		
		LocalDate entrada = func.getDataEntrada();
		
		if (func.getDataSaida() != null) {
			if (func.getDataSaida().isBefore(entrada)) {
				error.rejectValue("dataSaida", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}

}
