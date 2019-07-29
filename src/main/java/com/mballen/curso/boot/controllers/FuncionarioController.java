package com.mballen.curso.boot.controllers;

import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.mballen.curso.boot.modelos.Cargo;
import com.mballen.curso.boot.modelos.Funcionario;
import com.mballen.curso.boot.modelos.UF;
import com.mballen.curso.boot.repositorios.RepositorioCargo;
import com.mballen.curso.boot.repositorios.RepositorioFuncionario;
import com.mballen.curso.boot.validator.FuncionarioValidator;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private RepositorioFuncionario funcionarioRepository;
	
	@Autowired
	private RepositorioCargo cargo;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new FuncionarioValidator());
		
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("funcionario/lista");
		mv.addObject("funcionarios", funcionarioRepository.findAll());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@Valid Funcionario funcionario, BindingResult result, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		
		if (result.hasErrors()) {
			mv.setViewName("funcionario/cadastro");
			return mv;
			
		}
		mv.setViewName("redirect:/funcionarios/cadastrar");
		mv.addObject("funcionario", funcionarioRepository.save(funcionario));
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso");
		return mv;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("funcionario/cadastro");		
		mv.addObject("funcionario", funcionarioRepository.getOne(id));
		return mv;
	}
	
	@PostMapping("/editar")
	public ModelAndView editar(@Valid Funcionario f, BindingResult result, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();		
		
		if (result.hasErrors()) {
			mv.setViewName("funcionario/cadastro");
			return mv;
		}
		
		mv.addObject("funcionario", funcionarioRepository.save(f));
		attr.addFlashAttribute("success", "Funcionário editado com sucesso!");
		mv.setViewName("redirect:/funcionarios/cadastrar");
		return mv;
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();		
		funcionarioRepository.deleteById(id);
		mv.setViewName("redirect:/funcionarios/listar");
		return mv;
	}
	
	@GetMapping("/buscar/nome")
	public ModelAndView getPorNome(@RequestParam("nome") String nome, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("funcionarios", funcionarioRepository.findByName(nome));
		attr.addFlashAttribute("success", "Pesquisa realizada com sucesso!");
		mv.setViewName("funcionario/lista");
		return mv;
		
	}
	
	@GetMapping("/buscar/cargo")
	public ModelAndView getPorCargo(@RequestParam("id") Long id, RedirectAttributes attr) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("funcionarios", funcionarioRepository.findByCargo(id));
		mv.setViewName("funcionario/lista");
		return mv;
		
	}
	
	@GetMapping("buscar/data")
	public ModelAndView getPorData(@RequestParam("entrada") @DateTimeFormat(iso = ISO.DATE) LocalDate entrada,
									@RequestParam("saida") @DateTimeFormat(iso = ISO.DATE) LocalDate saida) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("funcionarios", funcionarioRepository.findByDate(entrada, saida));
		mv.setViewName("funcionario/lista");
		return mv;
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> listarCargos() {
		return cargo.findAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
		
	}
}
