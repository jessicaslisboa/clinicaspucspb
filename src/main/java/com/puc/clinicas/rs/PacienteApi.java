package com.puc.clinicas.rs;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.clinicas.models.Paciente;
import com.puc.clinicas.repository.PacienteRepositorio;

@RestController
@RequestMapping(value = "/paciente")
public class PacienteApi {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		List<Paciente> paciente = null;
		try {
			paciente = pacienteRepositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(paciente);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> getById(@PathParam("codPaciente") int codPaciente) {
		Paciente paciente = null;
		try {
			paciente = pacienteRepositorio.findById(codPaciente).get();
		} catch (Exception e) {
			throw e;
		}
		if(paciente !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(paciente);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@PostMapping
	public ResponseEntity<Object> salvar(Paciente paciente) {
		Paciente p = null;
		try {
			p = pacienteRepositorio.save(paciente);
		} catch (Exception e) {
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathParam("codPaciente") int codigo, Paciente paciente) {
		Paciente p = null;
		try {
			p = pacienteRepositorio.findById(codigo).get();
			if (p != null) {
				p = pacienteRepositorio.save(paciente);
			}
		} catch (Exception e) {
			throw e;
		}
		if(p !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(p);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> excluir(@PathParam("codigo") Integer codigo) {
		Paciente p = pacienteRepositorio.findById(codigo).get();
		if (p != null) {
			pacienteRepositorio.delete(p);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
