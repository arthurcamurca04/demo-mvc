package com.mballen.curso.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mballen.curso.boot.modelos.Cargo;
import com.mballen.curso.boot.repositorios.RepositorioCargo;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private RepositorioCargo cargoRepository;

	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Cargo cargo) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/cargos/cadastrar");
		model.addObject("cargo", cargoRepository.save(cargo));
		return model;		
	}
}
