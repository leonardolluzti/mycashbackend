package com.mycash.mycash;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.mycash.mycash.model.Despesa;
import com.mycash.mycash.model.Receita;
import com.mycash.mycash.repository.DespesaRepository;
import com.mycash.mycash.repository.ReceitaRepository;

@SpringBootApplication
public class MycashApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycashApplication.class, args);	
		
	}
	
	@Bean
	CommandLineRunner init(DespesaRepository despesaRepository, ReceitaRepository receitaRepository) {
		return args -> {				
			//Despesas
			despesaRepository.deleteAll();
			//@Query(value = "ALTER TABLE despesa AUTO_INCREMENT = 1;", nativeQuery = true) 			
			LongStream.range(1, 10)
			.mapToObj(i ->{
				Despesa desp = new Despesa();
				desp.setData(i+"-08-2021");
				desp.setDescricao("TAKARO");
				desp.setFixo(true);
				desp.setTipo("Outros");
				desp.setValor("R$ "+i+i+".00");
				return desp;
			})
			.map(m -> despesaRepository.save(m))
			.forEach(System.out::println); 	
			
			//Receitas
			receitaRepository.deleteAll();
			//@Query(value = "ALTER TABLE receita AUTO_INCREMENT = 1;", nativeQuery = true)
			LongStream.range(1, 10)
			.mapToObj(i ->{
				Receita r = new Receita();
				r.setData("0"+i+"-08-2021");
				r.setDescricao("TAKARO");
				r.setFixo(true);
				r.setTipo("Outros");
				r.setValor("R$ "+i+i+".00");
				return r;
			})
			.map(l -> receitaRepository.save(l))
			.forEach(System.out::println);
		};
	}

}
