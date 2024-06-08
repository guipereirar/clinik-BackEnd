package br.senai.sp.Clinik.controller;

import java.util.ArrayList;
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

import br.senai.sp.Clinik.model.Especialidade;
import br.senai.sp.Clinik.model.Medico;
import br.senai.sp.Clinik.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Autowired
	EspecialidadeRepository repositorio;
	
	@GetMapping
	public List<Especialidade> listar() {
		return repositorio.findAll();
	}
	
	@GetMapping("/{id}")
	public Especialidade listar(@PathVariable int id) {
		return repositorio.findById(id).orElseThrow();
	}	
	
	@PostMapping
	public Especialidade gravar(@RequestBody Especialidade espec) {
 		List<Medico> medTmp = espec.getMedico();
		espec.setMedico(null);
		Especialidade especNovo = repositorio.save(espec);
		
		List<Medico> tmp = new ArrayList<Medico>();
		for (Medico medico : medTmp) {
			medico.setEspecialidade(especNovo);
			tmp.add(medico);
		}
		especNovo.setMedico(tmp);
		
		return repositorio.save(especNovo);
	}

	@PutMapping("/{id}")
	public Especialidade alterar(@RequestBody Especialidade espec, @PathVariable int id) {
 		List<Medico> medTmp = espec.getMedico();
		espec.setMedico(null);
		Especialidade especNovo = repositorio.save(espec);
		
		List<Medico> tmp = new ArrayList<Medico>();
		for (Medico medico : medTmp) {
			medico.setEspecialidade(espec);
			tmp.add(medico);
		}
		especNovo.setMedico(tmp);
		
		return repositorio.save(especNovo);
	}	
	
	@DeleteMapping("/{id}")
	public void excluir(@PathVariable int id) {
		Especialidade tmp = null;
		if (repositorio.existsById(id)) {
			tmp = repositorio.findById(id).get();
			repositorio.delete(tmp);

		}
		
	}
}
