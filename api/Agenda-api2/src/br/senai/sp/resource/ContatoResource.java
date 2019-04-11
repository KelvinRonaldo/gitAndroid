package br.senai.sp.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.model.Contato;
import br.senai.sp.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
public class ContatoResource {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@GetMapping
	public List<Contato> getContatos(){
		return contatoRepository.findAll();
	}

}
