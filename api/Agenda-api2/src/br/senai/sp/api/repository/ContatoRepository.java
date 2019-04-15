package br.senai.sp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senai.sp.api.model.Contato;

//↓ MESMO QUE UM DAO, MAS COM A FRAMEWORK HIBERNATE NO SPRING 
public interface ContatoRepository extends JpaRepository<Contato, Long> {
//															↑		↑
//													TIPOS DE OBJETO	↑
//														TIPO DE IDENTIFICADOR DO OBJETO
}
