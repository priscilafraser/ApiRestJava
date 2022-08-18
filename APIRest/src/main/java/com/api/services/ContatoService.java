package com.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.entidades.Contato;
import com.api.repository.ContatoRepository;
import com.api.services.dto.ContatoDTO;
import com.api.services.exceptions.EmailValidoException;
//import com.api.services.exceptions.OperacaoNaoPermitidaException;

@Service
public class ContatoService {
	@Autowired   //ele que diz pra injetar o repository aqui	
	ContatoRepository repo;
	
	public ContatoDTO salvar(Contato contato) {
//		if(contato.getFone().length() != 14) {
//			throw new EmailValidoException("telefone invalido");
//		}
//		String email = contato.getEmail();
//		if(!email.contains("@")) {
//			throw new EmailValidoException("Email invalido");
//		}
		
		Contato ct = repo.save(contato);
		//ContatoDTO contatoDTO = new ContatoDTO(ct.getId(), ct.getNome(), ct.getEmail(), ct.getFone());
		ContatoDTO contatoDTO = new ContatoDTO(ct); 
		return contatoDTO;
			
	}
	
	public List<ContatoDTO> consultarContatos() {
		List<Contato> contatos = repo.findAll();
		List<ContatoDTO> contatosDTO = new ArrayList();
		for(Contato ct: contatos) {
			contatosDTO.add(new ContatoDTO(ct));    //recebe como construtor o ct
		}
		return contatosDTO;	
	}
	
	public ContatoDTO consultarContatoPorId(Long idcontato) {
		Optional<Contato> opcontato = repo.findById(idcontato);
		Contato ct = opcontato.orElseThrow(() -> new EntityNotFoundException("Contato nao encontrado"));
		return new ContatoDTO(ct);	
	}
	////////
	private Contato consultarContatoById(Long idcontato) {
		Optional<Contato> opcontato = repo.findById(idcontato);
		Contato ct = opcontato.orElseThrow(() -> new EntityNotFoundException("Contato nao encontrado"));
		return ct;	
	}
	///////////
	
	public void excluirContato(Long idcontato) {
//		Optional<Contato> opcontato = repo.findById(idcontato);
//		Contato ct = opcontato.orElseThrow(() -> new OperacaoNaoPermitidaException("O contatoNAO PODE SER EXCLUIDO!"));
//		repo.delete(ct);
		//////////////////
		repo.deleteById(idcontato);
		//////////////
//		Contato ct = consultarContatoPorId(idcontato);
//		repo.delete(ct);
	}
	
	public ContatoDTO alterarContato(Long idcontato, Contato contato) {
		Contato ct = consultarContatoById(idcontato);
		ct.setEmail(contato.getEmail());
		ct.setNome(contato.getNome());
		ct.setFone(contato.getFone());
		return new ContatoDTO(repo.save(ct));
	}
	
	public List<ContatoDTO> consultarContatoPorEmail(String email) {
		return ContatoDTO.converteParaDTO(repo.findByEmail(email));
	}
	
	

}
