package com.puc.clinicas.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exame", schema = "clinicaspuc")
public class Exame {

	@Id
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name = "nome")
	private String nome;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
