package com.api.services.dto;

import java.util.ArrayList;
import java.util.List;

import com.api.entidades.Contato;

public class ContatoDTO {

	private Long id;
	private String nome;
	private String email;
	private String fone;
	
	
	
	public ContatoDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public ContatoDTO(Long id, String nome, String email, String fone) {  //coloca id porque esse estamos pegando do banco de dados
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.fone = fone;
	}
	
	public ContatoDTO(Contato contato) {
		this.id = contato.getId();
		this.nome = contato.getNome();
		this.email = contato.getEmail();
		this.fone = contato.getFone();
		
	}
	
	public Long getId() {
		return id;
		
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public static List<ContatoDTO> converteParaDTO(List<Contato> contatos) {
		List<ContatoDTO> contatosDTO = new ArrayList<>();
		for(Contato ct: contatos) {
			contatosDTO.add(new ContatoDTO(ct));
		}
		return contatosDTO;
	}
	
	
}
