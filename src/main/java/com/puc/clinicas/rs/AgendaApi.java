package com.puc.clinicas.rs;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/api")
@Api(value = "Rest Agenda")

public class AgendaApi {
	
	@Autowired
	private AgendaRepositorio agendaRespositorio;
	
	
	@GetMapping("/agenda")
	public List<Agenda> getAll(){
		List<Agenda> agendas = null;
		try {
			agendas = agendaRespositorio.findAll();
		} catch (Exception e) {
			throw e;
		}
		return agendas;//Response.ok(agendas).build();
	}

	@GetMapping("/agenda/{codigo}")
	public Agenda getPorCodigo(@PathVariable(value = "codigo") int codigo) {
		Agenda agenda = null;
		try {
			agenda = agendaRespositorio.getById(codigo);
		} catch (Exception e) {
			throw e;
		}
		return agenda;
		/*if(agenda !=null) {
			return Response.ok(agenda).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}*/
	}	
	
	
	@PostMapping
	public Agenda salvar(@RequestBody Agenda agenda) {
		Agenda a = null;
		try {
			a = agendaRespositorio.save(agenda);
		} catch (Exception e) {
			throw e;
		}
		return a;
	}
	
	@DeleteMapping("/agenda/{codigo}")
	public Agenda excluir(@PathVariable(value = "codigo") Integer codigo) {
		Agenda m = agendaRespositorio.getById(codigo);
		if (m != null) {
			agendaRespositorio.delete(m);
		}/*else {
			//TODO Verificar status adequado
			return Response.status(Response.Status.NOT_FOUND).build();
		}*/
		return m;
		
	} 
	
	@PutMapping("/agenda/{codigo}")
	public Agenda atualizar(@PathVariable(value = "codigo") int codigo, @RequestBody Agenda agenda) {
		Agenda a = null;
		try {
			a = agendaRespositorio.getById(codigo);
			if (a != null) {
				a = agendaRespositorio.save(agenda);
			}
		} catch (Exception e) {
			throw e;
		}
		return a;
		/*if(a !=null) {
			return Response.ok(a).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}*/
	}
	

	
	/*
	 * @GET
	 * 
	 * @Path("/usuario/{codUsuario}") public Response
	 * getPorCodigoUsuario(@PathParam("codUsuario") int codUsuario){ List<Agenda>
	 * agendas = null; try { agendas =
	 * agendaService.oberAgendaPorUsuario(codUsuario); } catch (Exception e) { throw
	 * e; } return Response.ok(agendas).build(); }

	*/
	
	
	
}
