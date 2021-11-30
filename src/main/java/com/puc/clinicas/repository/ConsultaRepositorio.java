package com.puc.clinicas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.puc.clinicas.models.Consulta;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Integer> {

	@Query( value ="select c from Consulta c where c.realizada = false")
	public List<Consulta> obterConsultasNaoRealizadas();
	
	
}	
