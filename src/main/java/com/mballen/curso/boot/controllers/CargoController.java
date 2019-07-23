package com.mballen.curso.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mballen.curso.boot.modelos.Cargo;
import com.mballen.curso.boot.modelos.Departamento;
import com.mballen.curso.boot.repositorios.RepositorioCargo;
import com.mballen.curso.boot.repositorios.RepositorioDepartamento;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private RepositorioCargo cargoRepository;
	
	@Autowired
	private RepositorioDepartamento depRepository;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/cargo/lista";
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Cargo cargo, RedirectAttributes attr) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/cargos/cadastrar");
		model.addObject("cargo", cargoRepository.save(cargo));
		attr.addFlashAttribute("success", "Cargo inserido com sucesso!");
		return model;		
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listarDepartamentos(){
		return depRepository.findAll();
	}
}
