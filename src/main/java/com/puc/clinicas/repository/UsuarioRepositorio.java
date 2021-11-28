package com.puc.clinicas.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.puc.clinicas.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	
}	

