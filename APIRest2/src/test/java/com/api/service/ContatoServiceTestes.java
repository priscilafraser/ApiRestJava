package com.api.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.api.entidades.Contato;
import com.api.repository.ContatoRepository;
import com.api.services.ContatoService;
import com.api.services.dto.ContatoDTO;

@ExtendWith(SpringExtension.class)   //diz que nao queremos carregar o contexto da aplicação
public class ContatoServiceTestes {
	private Long idExistente;
	private Long idNaoExistente;
	private Contato contato;
	private Contato contatoValido;
	private Contato contatoInvalido;
	
	@BeforeEach
	void setup() {
		idExistente= 1l;
		idNaoExistente=1000l;
		contato = new Contato("maria", "maria@gmail.com","99999999999999");
		contatoInvalido = new Contato("maria", "maria@gmail.com","9999999");
		
		Mockito.doThrow(IllegalArgumentException.class).when(repository).save(contatoInvalido);
		
		//comportamentos simulados
		Mockito.when(repository.save(contato)).thenReturn(contato);
		
		Mockito.doNothing().when(repository).deleteById(idExistente);   //precisa porque nao esta acessando de fato, esta acessando o repositorio fake
		Mockito.doThrow(EntityNotFoundException.class).when(repository).deleteById(idNaoExistente);
		//Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(contato));
		
		Mockito.when(repository.findById(idExistente)).thenReturn(Optional.of(new Contato()));
		Mockito.doThrow(EntityNotFoundException.class).when(repository).findById(idNaoExistente);

	}
	
	@InjectMocks     //uitlizado p dizer o elemento que queremos testar
	ContatoService service;
	
	//para testar devemos injetar o repositorio, ele não acessa o repositorio original, somente simula o que tem lá
	@Mock      //diz p nao acessar o repo real
	ContatoRepository repository;
	
	@Test
	public void retornaExcecaoQuandoSalvarSemSuicesso() {
		Assertions.assertThrows(IllegalArgumentException.class, ()-> service.salvar(contatoInvalido));
		Mockito.verify(repository).save(contatoInvalido);
	}
	
	@Test
	public void retornaNadaAoExcluirComIdExistente() {
		ContatoDTO contatoDTO = service.salvar(contato);
		Assertions.assertNotNull(contatoDTO);
		Mockito.verify(repository).save(contato);
	}
	
	@Test
	public void retornaNadaAoExcluirIdExistente() {
		Assertions.assertDoesNotThrow(()-> {
			service.excluirContato(idExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoExcluirIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.excluirContato(idNaoExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).deleteById(idNaoExistente);
		
	}
	
//	@Test
//	public void retornaContatoQuandoIdExistente() {
//		Assertions.assertNotNull(service.consultarContatoById(idExistente));
//		Mockito.verify(repository,Mockito.times(1)).findById(idExistente);
//	}
	
	@Test
	public void retornaContatoQuandoIdExistente() {
		Contato ct = service.consultarContatoById(idExistente);
		Assertions.assertNotNull(ct);
		Mockito.verify(repository,Mockito.times(1)).findById(idExistente);
	}
	
	@Test
	public void lancaEntidadeNaoEncontradaAoConsultarIdNaoExistente() {
		Assertions.assertThrows(EntityNotFoundException.class, ()-> {
			service.consultarContatoById(idNaoExistente);
		});
		Mockito.verify(repository,Mockito.times(1)).findById(idNaoExistente);
		
	}
	
	

}
