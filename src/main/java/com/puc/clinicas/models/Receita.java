package com.puc.clinicas.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "receita", schema = "clinicaspuc")
public class Receita implements Serializable{
	
	private static final long serialVersionUID = 9108136410628584057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "receita_codigo_seq" )
	@SequenceGenerator(name = "receita_codigo_seq", allocationSize = 1, sequenceName = ("clinicaspuc.receita_codigo_seq"))
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@Column(name = "cod_paciente")
	private Paciente paciente;
	
	@Column(name = "cod_usuario")
	private Usuario usuario;
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}


}
