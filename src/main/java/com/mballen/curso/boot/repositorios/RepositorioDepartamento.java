package com.mballen.curso.boot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mballen.curso.boot.modelos.Departamento;

public interface RepositorioDepartamento extends JpaRepository<Departamento, Long>{

}
