package com.mballen.curso.boot.error;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyErrorView implements ErrorViewResolver{

	@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, 
			Map<String, Object> model) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("status", status.value());
		
		switch (status.value()) {
			case 404:
				mv.addObject("error", "Página não encontrada!");
				mv.addObject("message", "A url da página '" + model.get("path") + "' não existe");
				break;
				
			case 500:
				mv.addObject("error", "Ocorreu um erro interno no servidor!");
				mv.addObject("message", "Ocorreu um erro inesperado, tente mais tarde.");
				break;
	
			default:
				mv.addObject("error", model.get("error"));
				mv.addObject("message", model.get("message"));
				break;
			}
		return mv;
	}

}
