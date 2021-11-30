package com.puc.clinicas.rs;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.clinicas.models.Usuario;
import com.puc.clinicas.repository.UsuarioRepositorio;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "*")
public class UsuarioApi {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	//@Autowired
	//private PerfilRepositorio perfilRepositorio;
	
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioRepositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarios);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> getPorCodigo(@PathVariable(value = "codigo") Integer codigo) {
		Usuario usuario = null;
		try {
			usuario = usuarioRepositorio.findById(codigo).get();
		} catch (Exception e) {
			throw e;
		}
		if(usuario !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	/*@GetMapping("/perfil")
	public ResponseEntity<Object> listPerfil() {
		List<Perfil> perfis = null;
		try {
			perfis = usuarioRepositorio.getPerfilList();
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(perfis).build();
	}	
	
	@GetMapping("/perfil/{codigo}")
	public ResponseEntity<Object> listUsuariosPorPerfil(@PathParam("codigo") int codigo) {
		List<Usuario> usuarios = null;
		try {
			usuarios = usuarioRepositorio.obterUsuariosPerfil(codigo);
		} catch (Exception e) {
			throw e;
		}
		return Response.ok(usuarios).build();
	}*/	
	
	
	@PostMapping
	public ResponseEntity<Object> salvar(Usuario usuario) {
		Usuario u = null;
		try {
			u = usuarioRepositorio.save(usuario);
		} catch (Exception e) {
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(u);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "codigo") Integer codigo, @RequestBody Usuario usuario) {
		Usuario u = null;
		try {
			u = usuarioRepositorio.findById(codigo).get();
			if (u != null) {
				u = usuarioRepositorio.save(usuario);
			}
		} catch (Exception e) {
			throw e;
		}
		if(u !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "codigo") Integer codigo) {
		Usuario u = usuarioRepositorio.findById(codigo).get();
		if (u != null) {
			usuarioRepositorio.delete(u);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
