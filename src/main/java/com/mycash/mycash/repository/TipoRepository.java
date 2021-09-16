package com.mycash.mycash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycash.mycash.model.Tipo;

@Repository
public interface TipoRepository  extends JpaRepository<Tipo, Long> {

}
