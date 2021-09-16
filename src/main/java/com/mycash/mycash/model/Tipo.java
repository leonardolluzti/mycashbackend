package com.mycash.mycash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor	//Criação de construtores padrões.
@NoArgsConstructor	//Cria o construtor.
@Data	//Cria nossos gets e sets, assim como nossos hashcodes, etc.
@Entity
public class Tipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;	
	
}
