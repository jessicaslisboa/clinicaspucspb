package com.puc.clinicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.puc.clinicas.models.Exame;

@Repository
public interface ExameRepositorio extends JpaRepository<Exame, Integer> {


}	
