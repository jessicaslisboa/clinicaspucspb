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

import com.puc.clinicas.models.Endereco;
import com.puc.clinicas.models.Paciente;
import com.puc.clinicas.repository.EnderecoRepositorio;
import com.puc.clinicas.repository.PacienteRepositorio;

@RestController
@RequestMapping(value = "/paciente")
@CrossOrigin(origins = "*")
public class PacienteController {
	
	@Autowired
	private PacienteRepositorio pacienteRepositorio;
	
	@Autowired
	private EnderecoRepositorio enderecoRepositorio;
	
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
	public ResponseEntity<Object> getById(@PathVariable(value = "codigo") Integer codigo) {
		Paciente paciente = null;
		try {
			paciente = pacienteRepositorio.findById(codigo).get();
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
	public ResponseEntity<Object> salvar(@RequestBody Paciente paciente) {
		Paciente p = new Paciente();
		Endereco end = new Endereco();
		try {
			end = paciente.getEndereco();
			end = enderecoRepositorio.save(end);
			p.setEndereco(end);
			p = pacienteRepositorio.save(paciente);
		} catch (Exception e) {
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(p);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "codigo") Integer codigo,@RequestBody Paciente paciente) {
		Paciente p = null;
		try {
			p = pacienteRepositorio.findById(codigo).get();
			if (p != null) {
				if (paciente.getEndereco() != null) {
					Endereco end = paciente.getEndereco();
					end = enderecoRepositorio.save(end);
					p.setEndereco(end);
				}
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
	public ResponseEntity<Object> excluir(@PathVariable(value = "codigo") Integer codigo) {
		Paciente p = pacienteRepositorio.findById(codigo).get();
		if (p != null) {
			pacienteRepositorio.delete(p);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
