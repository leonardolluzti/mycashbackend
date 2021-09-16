package com.mycash.mycash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycash.mycash.model.Tipo;
import com.mycash.mycash.repository.TipoRepository;

@RestController
@RequestMapping({"/tipo"})
public class TipoController {
	@Autowired
	private TipoRepository repository;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/tipo
	public List findAll() {
		return repository.findAll();
	}
	@GetMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/tipo/{id}
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/tipo/
	public Tipo create(@RequestBody Tipo tipo) {
		return repository.save(tipo);
	}
	
	@PutMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/tipo/{id}
	public ResponseEntity update(@PathVariable long id, @RequestBody Tipo tipo) {
		return repository.findById(id)
				.map(record -> {				
					record.setDescricao(tipo.getDescricao());					
					Tipo update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());		
	}
	
	@DeleteMapping(path = {"/{id}"})
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/tipo/{id}
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}

}
