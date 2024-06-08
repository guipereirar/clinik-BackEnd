package br.senai.sp.Clinik.controller;

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

import br.senai.sp.Clinik.model.Medico;
import br.senai.sp.Clinik.repository.MedicoRepository;

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	MedicoRepository repositorio; 
	
	@GetMapping("/listar")
	public List<Medico> listar() {
		return repositorio.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Medico listar(@PathVariable int id) {
		return repositorio.findById(id).orElseThrow();
	}	
	
	@PostMapping("/gravar")
	public Medico gravar(@RequestBody Medico medico) {
		return repositorio.save(medico);
	}

	@PutMapping("/alterar/{id}")
	public Medico alterar(@RequestBody Medico medico, @PathVariable int id) {
		Medico m = repositorio.findById(id).get();
		medico.setId(m.getId());
		return repositorio.save(medico);
	}	
	
	@DeleteMapping("/excluir/{id}")
	public void excluir(@PathVariable int id) {
		Medico tmp = null;
		if (repositorio.existsById(id)) {
			tmp = repositorio.findById(id).get();
			repositorio.delete(tmp);
		};
		
		
		
	}

	
}
