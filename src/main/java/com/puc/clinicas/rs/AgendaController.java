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

import com.puc.clinicas.models.Agenda;
import com.puc.clinicas.repository.AgendaRepositorio;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/agenda")
@Api(value = "Rest Agenda")
@CrossOrigin(origins = "*")
public class AgendaController {
	
	@Autowired
	private AgendaRepositorio agendaRespositorio;
	
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		List<Agenda> agendas = null;
		try {
			agendas = agendaRespositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(agendas);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Object> getById(@PathVariable(value = "codigo") int codigo) {
		Agenda agenda = null;
		try {
			agenda = agendaRespositorio.findById(codigo).get();
		} catch (Exception e) {
			throw e;
		}
		
		if(agenda !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(agenda);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody Agenda agenda) {
		Agenda a = null;
		try {
			a = agendaRespositorio.save(agenda);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(a);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "codigo") Integer codigo) {
		Agenda m = agendaRespositorio.findById(codigo).get();
		if (m != null) {
			agendaRespositorio.delete(m);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		
	} 
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "codigo") int codigo, @RequestBody Agenda agenda) {
		Agenda a = null;
		try {
			a = agendaRespositorio.getById(codigo);
			if (a != null) {
				a = agendaRespositorio.save(agenda);
			}
		} catch (Exception e) {
			throw e;
		}
		if(a !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(a);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	  
	  @GetMapping("/usuario/{codUsuario}") 
	  public ResponseEntity<Object> getPorCodigoUsuario(@PathVariable(value = "codigo") int codigo){ 
		  List<Agenda> agendas = null; 
		  try { 
			  agendas = agendaRespositorio.findByUsuario(codigo); 
		  } catch (Exception e) { 
			  throw e; 
		  } 
		  return ResponseEntity.status(HttpStatus.OK).body(agendas);
	  }
	
}
