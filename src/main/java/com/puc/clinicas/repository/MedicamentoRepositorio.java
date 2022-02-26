package com.puc.clinicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puc.clinicas.models.Medicamento;

@Repository
public interface MedicamentoRepositorio extends JpaRepository<Medicamento, Integer>{



}	
