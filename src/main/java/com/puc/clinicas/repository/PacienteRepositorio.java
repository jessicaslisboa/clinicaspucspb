package com.puc.clinicas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.clinicas.models.Paciente;


public interface PacienteRepositorio extends JpaRepository<Paciente, Integer>{

	

}	
