package br.senai.sp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// @ → Anotação(tem que ficar acima da linha a qual a anotação se refere)
@Entity // ← Dizendo ao Spring que a classe é uma tabela do banco
@Table(name = "tbl_contato") // ← Dizendo ao Spring qual o nome da tabela caso o nome da tabela seja diferente do nome da class
public class Contato {

	@Id	// Dizendo para o Spring que o atributo de classe 'id' é o identificador(Id) da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// Dizendo para o Spring esta campo é gerado automaticamente(IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;


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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}
	
	

}
