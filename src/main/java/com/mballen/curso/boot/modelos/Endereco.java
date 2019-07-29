package com.mballen.curso.boot.modelos;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name = "enderecos")
public class Endereco extends AbstractEntity<Long> {
	
	@NotBlank(message = "Informe o logradouro.")
	@Size(min = 5, max = 255, message = "O campo de conter no minímo {min} e no máximo {max} caracteres.")
	@Column(nullable = false)
	private String logradouro;
	
	@NotBlank(message = "Informe o bairro")
	@Size(min = 3, max = 255, message = "O campo de conter no minímo {min} e no máximo {max} caracteres.")
	@Column(nullable = false)
	private String bairro;
	
	@NotBlank(message = "Informe a cidade." )
	@Size(min = 3, max = 255, message = "O campo de conter no minímo {min} e no máximo {max} caracteres.")
	@Column(nullable = false)
	private String cidade;
	
	@NotNull(message = "UF obrigatório")
	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;
	
	@NotBlank(message = "CEP obrigatório.")
	@Size(min = 9, max = 9, message = "Deve conter 9 caracteres.")
	@Column(nullable = false, length = 9)
	private String cep;
	
	@NotNull(message = "Número obrigatório.")
	@Digits(integer = 5, fraction = 0)
	@Column(nullable = false, length = 5)
	private Integer numero;
	
	@Size(max = 255, message = "O campo de conter no máximo {max} caracteres.")
	private String complemento;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
}
