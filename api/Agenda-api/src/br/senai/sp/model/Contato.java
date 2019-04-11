package br.senai.sp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//ANOTAÇÃO QUE DIZ PRO JAVA QUE ESSA CLASSE É UMA ENTIDADE NO BANCO DE DADOS
@Entity
//ANOTAÇÃO QUE DIZ PRO JAVA QUE O NOME DA TABLE NO BANCO
@Table(name = "tbl_contato")

public class Contato {

	//ANOTAÇÃO QUE DIZ PRO JAVA QUE ESSE ATRIBUTO É O IDENTIFICADOR DA TABELA
	@Id
	//ANOTAÇÃO QUE DIZ PRO JAVA QUE ESSE ATRIBUTO É AUTO INCREMENTAL
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String endereco;
	private String telefone;
	private String email;
	private String linkedin;
	private String foto;

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
