package com.mycash.mycash.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycash.mycash.model.User;
import com.mycash.mycash.repository.UserRepository;

@RestController
@RequestMapping({"/user"})
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@GetMapping
	// http://localhost:9000/user
	public List findAllRecipes() {
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	// http://localhost:9000/user/{id}
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	// http://localhost:9000/user/
	public User create(@RequestBody User user) {
		return repository.save(user);
	}
	
	@PutMapping(value = "{id}")
	// http://localhost:9000/user/{id}
	public ResponseEntity update(@PathVariable long id, @RequestBody User user) {
		return repository.findById(id)
				.map(record -> {
					record.setUsername(user.getUsername());
					record.setPassword(user.getPassword());
					record.setAdmin(user.isAdmin());
					User update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());		
	}
	
	@DeleteMapping(path = {"/{id}"})
	// http://localhost:9000/user/{id}
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
}
