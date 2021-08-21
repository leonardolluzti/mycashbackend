package com.mycash.mycash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycash.mycash.repository.DespesaRepository;

@RestController
@RequestMapping({"/despesa"})
public class DespesaController {
	
	@Autowired
	private DespesaRepository repository;
	
	@GetMapping
	// http://localhost:9000/despesa
	public List findAllInvoices() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	// http://localhost:9000/despesa/{id}
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

}
