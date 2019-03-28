package br.senai.sp.catalogodefilmes;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {
    private EditText txtTitulo;
    private EditText txtDate;
    private EditText txtDiretor;
    private EditText txtDuracao;
    private EditText txtGenero;
    private RatingBar nota;
    private Filme filme;

    public CadastroFilmeHelper(CadastroActivity activity){
        //Classe ajudante, usamos esta classe para pegar os dados dos views e usar para salvar no banco
        txtTitulo = activity.findViewById(R.id.txt_titulo);
        txtDate = activity.findViewById(R.id.txt_data);
        txtDiretor = activity.findViewById(R.id.txt_diretor);
        txtDuracao = activity.findViewById(R.id.txt_duracao);
        txtGenero = activity.findViewById(R.id.txt_genero);
        nota = activity.findViewById(R.id.rating_nota);
        filme = new Filme();
    }

    public Filme getFilme(){
        filme.setTitulo(txtTitulo.getText().toString());
        filme.setDataLancamento(txtDate.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setNota(nota.getProgress());

        return filme;
    }

    public void preencherFormulario(Filme filme){
        txtTitulo.setText(filme.getTitulo());
        txtDiretor.setText(filme.getDiretor());
        txtDate.setText(filme.getDataLancamento());
        txtDuracao.setText(filme.getDuracao());
        txtGenero.setText(filme.getGenero());
        nota.setProgress(filme.getNota());
        this.filme = filme;
    }
}






