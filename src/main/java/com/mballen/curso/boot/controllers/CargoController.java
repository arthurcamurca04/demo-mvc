package com.mballen.curso.boot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/cargo/lista");
		mv.addObject("cargos", cargoRepository.findAll());
		return mv;
		
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
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Cargo cargo = cargoRepository.getOne(id);
		mv.addObject("cargo",cargo);
		mv.setViewName("/cargo/cadastro");
		return mv;
		
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(Cargo cargo, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("cargo", cargoRepository.save(cargo));
		attr.addFlashAttribute("success", "Cargo editado com sucesso!");
		mv.setViewName("redirect:/cargos/cadastrar");
		return mv;
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		if (cargoRepository.getOne(id).getFuncionario().isEmpty()) {
			Cargo cargo = cargoRepository.getOne(id);
			cargoRepository.delete(cargo);
			mv.setViewName("redirect:/cargos/listar");
			attr.addFlashAttribute("success", "Cargo excluído com sucesso!");
		}else {
			mv.setViewName("redirect:/cargos/listar");
			attr.addFlashAttribute("fail", "Cargo não excluído, pois possui funcionário(s) vinculado(s).");
		}
		return mv;
	}
}
