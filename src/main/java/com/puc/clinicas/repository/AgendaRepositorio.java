package com.puc.clinicas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.puc.clinicas.models.Agenda;

@Repository
public interface AgendaRepositorio extends JpaRepository<Agenda, Integer>{

	@Query(value ="select a from Agenda a where a.usuario.codigo = :codigo" )
	public List<Agenda> findByUsuario(Integer codigo);

}	
