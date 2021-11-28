package com.puc.clinicas.models;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paciente_exame", schema = "clinicaspuc")
public class PacienteExame implements Serializable{
	
	private static final long serialVersionUID = 9108136410628584057L;

	@Id
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_paciente")
	private Paciente paciente;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_exame")
	private Exame exame;
	
	public Integer getCodigo() {
		return codigo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


}
