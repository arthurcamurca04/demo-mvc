package com.mballen.curso.boot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView salvar(Departamento departamento, RedirectAttributes attr) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/departamentos/cadastrar");
		model.addObject("departamento", depRepository.save(departamento));
		attr.addFlashAttribute("success", "Departamento inserido com sucesso!");
		return model;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("departamento", depRepository.getOne(id));
		mv.setViewName("/departamento/cadastro");
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(Departamento departamento, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("departamento", depRepository.save(departamento));
		attr.addFlashAttribute("success", "Departamento editado com sucesso!");
		mv.setViewName("redirect:/departamentos/cadastrar");
		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		if (depRepository.getOne(id).getCargos().isEmpty()) {
			Departamento departamento = depRepository.getOne(id);
			depRepository.delete(departamento);
			mv.setViewName("redirect:/departamentos/listar");
			attr.addFlashAttribute("success", "Departamento excluído com sucesso!");
		}else {
			mv.setViewName("redirect:/departamentos/listar");
			attr.addFlashAttribute("fail", "Departamento não excluído, pois possui cargo(s) vinculado(s).");
		}
		return mv;
	}


}
