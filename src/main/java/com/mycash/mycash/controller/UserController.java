package com.mycash.mycash.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@RequestMapping("/login")
	@GetMapping
	// http://localhost:9000/login
	public String login(HttpServletRequest request){
		String token = request.getHeader("Authorization")
				.substring("Basic".length()).trim();
		return token;		
	}
/*	
	@RequestMapping("/login1")
	@GetMapping
	// http://localhost:9000/login1
	public List<String> login2(HttpServletRequest request) throws UnsuportedEncodingException{
		// String t0 = request;
		// System.out.println(t0);
		String t1 = request.getHeader("Authorization");
		System.out.println(t1);
		String t2 = request.getHeader("Authorization").substring("Basic".length());
		System.out.println(t2);
		
		//ESTUDO DE CRIPTOGRAFIA
		
	}
*/	
	//Lista todos os usuários
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/user
	public List findAll() {
		return repository.findAll();
	}
	
	//Retorna usuário por id
	@GetMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/user/{id}
	public ResponseEntity findById(@PathVariable long id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	//Criar novo usuário
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/user/
	public User create(@RequestBody User user) {
		user.setPassword(criptografarSenha(user.getPassword()));
		return repository.save(user);
	}
	
	//Atualizar usuário
	@PutMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/user/{id}
	public ResponseEntity update(@PathVariable long id, @RequestBody User user) {
		return repository.findById(id)
				.map(record -> {
					record.setUsername(user.getUsername());
					record.setPassword(criptografarSenha(user.getPassword()));
					record.setAdmin(user.isAdmin());
					User update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());		
	}
	
	//Deletar Usuário
	@DeleteMapping(path = {"/{id}"})
	@PreAuthorize("hasRole('ADMIN')")
	// http://localhost:9000/user/{id}
	public ResponseEntity<?> delete(@PathVariable long id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Deletado com sucesso!");
				}).orElse(ResponseEntity.notFound().build());
	}
	
	// Criptografar Senha
	private String criptografarSenha(String password) {
		BCryptPasswordEncoder passwordEnconder = new BCryptPasswordEncoder();
		String passwordParaCriptografar = passwordEnconder.encode(password);
		return passwordParaCriptografar;
	}
}
