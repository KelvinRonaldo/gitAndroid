package br.senai.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
