package br.senai.sp.modelo;

import java.io.Serializable;
//PARA UM OBJETO SER TRANSPORTADO DE UMA ACTIVITY À OUTRA, A CLASSE DEVE SER SERIALIZADA
//                                ↓ A classe Filme vai implementar a Serializable para que possa ser serializada.
public class Filme implements Serializable {
    private int id;
    private String titulo;
    private String diretor;
    private String dataLancamento;
    private String duracao;
    private String genero;
    private int nota;
    private byte[] foto;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(String dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return this.titulo + " - " + this.nota;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
