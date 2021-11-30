package com.puc.clinicas.rs;


import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.puc.clinicas.models.Medicamento;
import com.puc.clinicas.repository.MedicamentoRepositorio;

@RestController
@RequestMapping(value = "/medicamento")
@CrossOrigin(origins = "*")
public class MedicamentoApi {
	
	@Autowired
	private MedicamentoRepositorio medicamentoRepositorio;
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		List<Medicamento> medicamentos = null;
		try {
			medicamentos = medicamentoRepositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(medicamentos);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> getById(@PathParam("codigo") int codigo) {
		Medicamento medicamento = null;
		try {
			medicamento = medicamentoRepositorio.findById(codigo).get();
		} catch (Exception e) {
			throw e;
		}
		if(medicamento !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(medicamento);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@PostMapping
	public ResponseEntity<Object> salvar(Medicamento medicamento) {
		Medicamento m = null;
		try {
			m = medicamentoRepositorio.save(medicamento);
		} catch (Exception e) {
			throw e;
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(m);
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathParam("codigo") int codigo, Medicamento medicamento) {
		Medicamento m = null;
		try {
			m = medicamentoRepositorio.findById(codigo).get();
			if (m != null) {
				m = medicamentoRepositorio.save(medicamento);
			}
		} catch (Exception e) {
			throw e;
		}
		if(m !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(m);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> excluir(@PathParam("codigo") Integer codigo) {
		Medicamento m = medicamentoRepositorio.findById(codigo).get();
		if (m != null) {
			medicamentoRepositorio.delete(m);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	}
	
}
