package com.puc.clinicas.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "perfil", schema = "clinicaspuc")
public class Perfil {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="perfil_codigo_seq")
	@SequenceGenerator(name = "perfil_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.perfil_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name="nome")
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
