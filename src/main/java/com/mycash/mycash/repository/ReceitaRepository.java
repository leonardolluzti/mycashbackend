package com.mycash.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycash.mycash.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
