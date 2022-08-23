package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.entidades.Contato;

@Repository    //diz que posso injetar em outros lugares
public interface ContatoRepository extends JpaRepository<Contato, Long> {  //long é o tipo de dado que defini no id
//esse cara que faz toda a persistencia
	List<Contato> findByEmail(String email);
}

//public interface ContatoRepository extends CrudRepository<Contato, Long> {  //long é o tipo de dado que defini no id
//	//esse cara que faz toda a persistencia
//	}
