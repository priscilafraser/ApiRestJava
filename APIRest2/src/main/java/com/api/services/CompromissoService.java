package com.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entidades.Compromisso;
import com.api.repository.CompromissoRepository;
import com.api.services.dto.ContatoDTO;

@Service
public class CompromissoService {
	
	@Autowired
	CompromissoRepository repo;
	
	public Compromisso salvar(Compromisso compromisso) {

		Compromisso ct = repo.save(compromisso);
		//ContatoDTO contatoDTO = new ContatoDTO(ct.getId(), ct.getNome(), ct.getEmail(), ct.getFone());
		return compromisso;
			
	}

}
