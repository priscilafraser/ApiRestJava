package com.api.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

@Entity
public class Compromisso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "O local deve ser informado")
	private String local;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	//nao posso excluir um compromisso se tiver um contato vinculado a ele
	@ManyToOne    //coloc em compromisso a referncia de contato
	private Contato contato;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Compromisso(@NotBlank(message = "O local deve ser informado") String local, Date data, Contato contato) {
		this.local = local;
		this.data = data;
		this.contato = contato;
	}

	public Compromisso() {
		// TODO Auto-generated constructor stub
	}
	
	

}
