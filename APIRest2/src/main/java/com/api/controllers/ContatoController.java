package com.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.entidades.Contato;
import com.api.services.ContatoService;
import com.api.services.dto.ContatoDTO;

@RestController
@RequestMapping("/")
public class ContatoController {
	//injetar aqui
//	@Autowired    //spring se responsabiliza por criar esse repo, dizendo que é um objeto de ContatoRepository, só é possivel utiliza-lo porque colocamos @Repository lá
//	ContatoRepository repo;
	
	@Autowired
	ContatoService service;
	
	@GetMapping
	public String xpto() {
		return "Index de contato";
	}
	
	@GetMapping("/contatos/email/{email}")
	public ResponseEntity<List<ContatoDTO>> getContatosPorEmail(@PathVariable("email") String email) {
		return ResponseEntity.ok(service.consultarContatoPorEmail(email));
	}
	
	@GetMapping("/contato")
	public ResponseEntity<List<ContatoDTO>> getContatos() {
		List<ContatoDTO> contatos = service.consultarContatos();
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}
	
	@GetMapping("/contato/{idcontato}")
	public ResponseEntity<ContatoDTO> getContatosById(@PathVariable("idcontato") Long idcontato) {
		return ResponseEntity.ok(service.consultarContatoPorId(idcontato));
	}
	
	@PostMapping("/contato")
	public ResponseEntity<ContatoDTO> saveContato(@Valid @RequestBody Contato contato) { //pega o corpo da requisicao e guarda na variavel contato
		ContatoDTO ct = service.salvar(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(ct);
	}
	
	@DeleteMapping("contato/{idcontato}")
	public ResponseEntity<Void> deleteContato(@PathVariable("idcontato") Long	 idcontato) {
		service.excluirContato(idcontato);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("contato/{idcontato}")
	public ResponseEntity<ContatoDTO> alteraContato(@PathVariable("idcontato") Long idcontato, @RequestBody Contato contato) {
		return ResponseEntity.ok(service.alterarContato(idcontato, contato));
	}
	

}
