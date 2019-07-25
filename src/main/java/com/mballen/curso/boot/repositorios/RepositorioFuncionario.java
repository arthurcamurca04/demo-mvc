package com.mballen.curso.boot.repositorios;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mballen.curso.boot.modelos.Funcionario;

public interface RepositorioFuncionario extends JpaRepository<Funcionario, Long> {

	@Query("SELECT f FROM Funcionario f WHERE f.nome = :nome")
	public List<Funcionario> findByName(@Param("nome") String nome);

	@Query("SELECT f FROM Funcionario f WHERE f.cargo.id = :id")
	public List<Funcionario> findByCargo(@Param("id") Long id);
	
	@Query("SELECT f FROM Funcionario f WHERE f.dataEntrada = :entrada AND f.dataSaida = :saida")
	public List<Funcionario> findByDate(@Param("entrada") LocalDate entrada, 
										@Param("saida") LocalDate saida);

}
