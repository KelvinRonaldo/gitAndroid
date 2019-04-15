package br.senai.sp.api.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Contato;
import br.senai.sp.api.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@Autowired //FALA PRO SPRING INSTANCIAR ESSE OBJETO(FAZ O new...)
	private ContatoRepository contatoRepository;
	
// 	↓ QUANDO O TIPO DA REQUISIÇÃO FOR 'GET' ELE EXECUTA ESSE MÉTODO
	@GetMapping
	public List<Contato> getContatos(){
		return contatoRepository.findAll();//← MESMO QUE UM SELECT, MAS COM A FRAMEWORK HIBERNATE NO SPRING
	}
// 	↓ QUANDO O TIPO DA REQUISIÇÃO FOR 'POST' ELE EXECUTA ESSE MÉTODO
	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED) // FAZ COM QUE A RESPOSTA DA REQUISIÇÃO RETORNE COM STATUS 'Created(201)' AO INVÉS DE 'Ok(200)'
	public ResponseEntity<Contato> gravar(@RequestBody Contato contato, HttpServletResponse response) {//← MESMO QUE UM INSERT, MAS COM A FRAMEWORK HIBERNATE NO SPRING
//												↑ ANOTANDO QUE O PARAMETRO(Contato) ESTA NO BODY DA REQUISIÇÃO
		Contato contatoSalvo = contatoRepository.save(contato); // SALVANDO CONTATO, O .save RECEBE O CONTATO QUE FOI SALVO PELO .save

//		CRIANDO URI COM UMA CLASSE CONSTRUTORA DE URI COM A URI LOCAL
		URI uri = ServletUriComponentsBuilder //CLASSE CONSTRUTORA
		.fromCurrentRequestUri() //URI LOCAL, URI QUE 'EU ESTOU'
		.path("/{id}") //ACRESANTANDO ELEMENTO À URI ONDE id RECEBERA ↓
		.buildAndExpand(contatoSalvo.getId())// PARAMAETRO QUE SERA COLOCADO NO .path
		.toUri();
		
//		ADICIONANDO UM CABEÇALHO NO RESPONSE REDIRECIONANDO A PAGINA PARA A URI CRIADA
		response.addHeader("Location", uri.toASCIIString());
		
//		RETORNANDO A RESPOSTA(response) COM STATUS created NA NOVA URI E NO CORPO, O CONTATO RETORNADO
//													↑ POR ISSO A ANOTAÇÃO 'ResponseStatus' ESTA COMENTADA
		return ResponseEntity.created(uri).body(contatoSalvo);
	}

	
	@GetMapping("/{id}")
	public Optional<Contato> getContato(@PathVariable Long id) {
		
		return contatoRepository.findById(id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteContato(@PathVariable Long id) {
		contatoRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public void atualizarContato(Contato contato, Long id) {
		contatoRepository.
		
	}
	
}










