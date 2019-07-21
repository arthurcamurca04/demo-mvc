package com.mballen.curso.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mballen.curso.boot.modelos.Departamento;
import com.mballen.curso.boot.repositorios.RepositorioDepartamento;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private RepositorioDepartamento depRepository;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		
		ModelAndView model = new ModelAndView();
		model.setViewName("/departamento/lista");
		model.addObject("departamentos", depRepository.findAll());
		return model;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Departamento departamento) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/departamentos/cadastrar");
		model.addObject("departamento", depRepository.save(departamento));
		return model;
	}

}
