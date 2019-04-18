package br.senai.sp.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// @ → Anotação(tem que ficar acima da linha a qual a anotação se refere)
@Entity // ← Dizendo ao Spring que a classe é uma tabela do banco
@Table(name = "tbl_contato") // ← Dizendo ao Spring qual o nome da tabela caso o nome da tabela seja
								// diferente do nome da class
public class Contato {

	@Id // Dizendo para o Spring que o atributo de classe 'id' é o identificador(Id) da tabela
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Dizendo para o Spring esta campo é gerado automaticamente(IDENTITY)
	private Long id;
	
	@NotNull 				// Dizendo que campo não pode ser nulo
	@Size(min=5, max=100) // Dizendo tamanho minimo e máximo do campo
	private String nome;
	@NotNull 				
	@Size(min=5, max=100)
	private String endereco;
	@NotNull 				
	@Size(min=5, max=100)
	private String email;
	@NotNull 				
	@Size(min=5, max=20)
	private String telefone;
	@NotNull 				
	@Size(min=5, max=100)
	private String linkedin;
	@NotNull 				
	@Size(min=5, max=100)
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
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

	@Override
	public String toString() {
		return "Contato [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", email=" + email + ", telefone="
				+ telefone + ", linkedin=" + linkedin + ", foto=" + foto + "]";
	}
}
