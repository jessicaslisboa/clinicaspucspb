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

import com.puc.clinicas.models.Consulta;
import com.puc.clinicas.models.Exame;
import com.puc.clinicas.repository.ConsultaRepositorio;
import com.puc.clinicas.repository.ExameRepositorio;

@RestController
@RequestMapping(value = "/consulta")
@CrossOrigin(origins = "*")
public class ConsultaController {
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
	
	@Autowired
	private ExameRepositorio exameRepositorio;
	
	@GetMapping()
	public ResponseEntity<Object> getAll(){
		List<Consulta> consultas = null;
		try {
			consultas = consultaRepositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(consultas);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Object> getById(@PathVariable(value = "codigo") int codigo) {
		Consulta consulta = null;
		try {
			consulta = consultaRepositorio.findById(codigo).get();
		} catch (Exception e) {
			throw e;
		}
		if(consulta !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(consulta);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	@GetMapping("/pendentes") 
	public ResponseEntity<Object> getPorCodigoUsuario(){ 
		List<Consulta> consultas = null; 
		try { 
			consultas = consultaRepositorio.obterConsultasNaoRealizadas(); 
		} catch (Exception e) { 
			throw e; 
		} 
		return ResponseEntity.status(HttpStatus.OK).body(consultas);
	 }

	@GetMapping("/exames") 
	public ResponseEntity<Object> getExamesList(){ 
		List<Exame> exames = null; 
		try { 
			exames = exameRepositorio.findAll(); 
		} catch (Exception e) { 
			throw e; 
		} 
		return ResponseEntity.status(HttpStatus.OK).body(exames);
	 }
	 
	
	/*
	 * @GET
	 * 
	 * @Path("/paciente/{codPaciente}") public Response
	 * getPorCodigoPaciente(@QueryParam("codPaciente") int codPaciente){
	 * List<Consulta> consultas = null; try { consultas =
	 * consultaService.oberPorPaciente(codPaciente); } catch (Exception e) { throw
	 * e; } //TODO tratar usuario ? return Response.ok(consultas).build(); }
	 */
	
	@PostMapping
	public ResponseEntity<Object> salvar(@RequestBody Consulta consulta) {
		Consulta c = null;
		try {
			c = consultaRepositorio.save(consulta);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathVariable(value = "codigo") int codigo,@RequestBody Consulta consulta) {
		Consulta c = null;
		try {
			c = consultaRepositorio.findById(codigo).get();
			if (c != null) {
				c = consultaRepositorio.save(consulta);
			}
		} catch (Exception e) {
			throw e;
		}
		if(c !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Object> excluir(@PathVariable(value = "codigo") Integer codigo) {
		Consulta c = consultaRepositorio.findById(codigo).get();
		if (c != null) {
			consultaRepositorio.delete(c);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
}
