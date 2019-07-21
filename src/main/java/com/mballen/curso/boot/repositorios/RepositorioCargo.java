package com.mballen.curso.boot.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mballen.curso.boot.modelos.Cargo;

public interface RepositorioCargo extends JpaRepository<Cargo, Long> {

}
