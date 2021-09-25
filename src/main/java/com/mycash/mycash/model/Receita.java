package com.mycash.mycash.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receita{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String valor;
	//private long tipo;
	private String data;
	private String descricao;
	private boolean fixo;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo", referencedColumnName = "id")
	@JsonIgnore
	private Tipo tipo;
		
}
