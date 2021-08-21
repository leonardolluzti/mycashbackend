package com.mycash.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycash.mycash.model.Despesa;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{

}
