package com.mballen.curso.boot.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "funcionarios")
public class Funcionario extends AbstractEntity<Long> {

	@NotBlank(message = "Informe um nome.")
	@Size(min = 2, max = 255, message = "O nome de funcionário deve conter no mínimo {min} e no máximo {max} caracteres.")
	@Column(nullable = false, unique = true)
	private String nome;
	
	@NotNull(message = "O campo sálario preciser ser informado.")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal salario;
	
	@NotNull(message = "A data de entrada precisa ser informada.")
	@PastOrPresent(message = "A data deve ser igual ou anterior a atual.")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_entrada", nullable = false, columnDefinition = "DATE")
	private LocalDate dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_saida", columnDefinition = "DATE", nullable = true)
	private LocalDate dataSaida;
	
	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_fk")
	private Endereco endereco;
	
	@NotNull(message = "Selecione um cargo")
	@ManyToOne
	@JoinColumn(name = "cargo_id_fk")
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}	
}
