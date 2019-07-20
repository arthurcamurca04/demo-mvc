package com.mballen.curspo.boot.dao;

import java.util.List;

import com.mballen.curso.boot.domain.Funcionario;

public interface FuncionarioDAO {
	
	public void save(Funcionario f);
	public void update(Funcionario f);
	public void delete(Long id);
	public Funcionario findById(Long id);
	public List<Funcionario> findAll();
}
