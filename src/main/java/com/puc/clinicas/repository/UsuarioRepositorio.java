package com.puc.clinicas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.puc.clinicas.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Query(value ="select u from Usuario u where u.perfil.codigo = :codigo" )
	public List<Usuario> obterUsuariosPerfil(Integer codigo);
	
	@Query(value ="select u from Usuario u where u.email = :email")
	public Usuario obterPorEmail(String email);
}	

