package com.puc.clinicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puc.clinicas.models.Usuario;
import com.puc.clinicas.models.UsuarioLogin;
import com.puc.clinicas.repository.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	
	public boolean verificaLogin(UsuarioLogin usuario) {
		Usuario user = usuarioRepositorio.obterPorEmail(usuario.getEmail());
		if(user != null) {
			if(user.getSenha().equals(usuario.getSenha())){
				return true;
			}
		}
		return false;
	}

}
