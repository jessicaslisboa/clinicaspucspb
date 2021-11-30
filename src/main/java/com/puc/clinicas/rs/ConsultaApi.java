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

import com.puc.clinicas.models.Consulta;
import com.puc.clinicas.repository.ConsultaRepositorio;

@RestController
@RequestMapping(value = "/consulta")
@CrossOrigin(origins = "*")
public class ConsultaApi {
	
	@Autowired
	private ConsultaRepositorio consultaRepositorio;
	
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
	public ResponseEntity<Object> getById(@PathParam("codPaciente") int codPaciente) {
		Consulta consulta = null;
		try {
			consulta = consultaRepositorio.findById(codPaciente).get();
		} catch (Exception e) {
			throw e;
		}
		if(consulta !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(consulta);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}	
	
	/*@GetMapping("/pendentes") 
	public ResponseEntity<Object> getPorCodigoUsuario(){ 
		List<Consulta> consultas = null; 
		try { 
			consultas = consultaRepositorio.obterConsultasNaoRealizadas(); 
		} catch (Exception e) { 
			throw e; 
		} 
		//TODO tratar usuario ? 
		return Response.ok(consultas).build(); 
	 }

	@GetMapping("/exames") 
	public ResponseEntity<Object> getExamesList(){ 
		List<Exame> exames = null; 
		try { 
			exames = consultaRepositorio.listaExames(); 
		} catch (Exception e) { 
			throw e; 
		} 
		return Response.ok(exames).build(); 
	 }
	 */
	
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
	public ResponseEntity<Object> salvar(Consulta consulta) {
		Consulta c = null;
		try {
			c = consultaRepositorio.save(consulta);
		} catch (Exception e) {
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(c);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Object> atualizar(@PathParam("codPaciente") int codigo,Consulta consulta) {
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
	public ResponseEntity<Object> excluir(@PathParam("codigo") Integer codigo) {
		Consulta c = consultaRepositorio.findById(codigo).get();
		if (c != null) {
			consultaRepositorio.delete(c);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
}
