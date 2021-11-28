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
@Table(name = "receita_medicamento", schema = "clinicaspuc")
public class ReceitaMedicamento implements Serializable{
	
	private static final long serialVersionUID = 9108136410628584057L;

	@Id
	@Column(name = "codigo", unique = true, nullable = false)	
	private Integer codigo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_receita")
	private Receita receita;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cod_medicamento")
	private Medicamento medicamento;
	
	public Integer getCodigo() {
		return codigo;
	}

	public Receita getReceita() {
		return receita;
	}

	public void setReceita(Receita receita) {
		this.receita = receita;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


}
