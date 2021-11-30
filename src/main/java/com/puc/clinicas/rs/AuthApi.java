package com.puc.clinicas.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.clinicas.models.UsuarioLogin;
import com.puc.clinicas.service.UsuarioService;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class AuthApi {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Object> login(@RequestBody UsuarioLogin usuario) {
		try{
			if (usuarioService.verificaLogin(usuario)) {
				return new ResponseEntity<Object>(HttpStatus.OK);
            }else {
	    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário e/ou senha inválidos!");
            }
		}catch(Exception ex){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		} 
		
	}
}
