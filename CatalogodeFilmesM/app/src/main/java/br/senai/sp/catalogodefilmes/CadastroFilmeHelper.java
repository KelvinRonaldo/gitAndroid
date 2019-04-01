package br.senai.sp.catalogodefilmes;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.StreamHandler;

import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Filme;

public class CadastroFilmeHelper {
    private EditText txtTitulo;
    private EditText txtDate;
    private EditText txtDiretor;
    private EditText txtDuracao;
    private EditText txtGenero;
    private RatingBar nota;
    private ImageView imgFilme;
    private Filme filme;

    public CadastroFilmeHelper(CadastroActivity activity){
        //Classe ajudante, usamos esta classe para pegar os dados dos views e usar para salvar no banco
        txtTitulo = activity.findViewById(R.id.txt_titulo);
        txtDate = activity.findViewById(R.id.txt_data);
        txtDiretor = activity.findViewById(R.id.txt_diretor);
        txtDuracao = activity.findViewById(R.id.txt_duracao);
        txtGenero = activity.findViewById(R.id.txt_genero);
        nota = activity.findViewById(R.id.rating_nota);
        imgFilme = activity.findViewById(R.id.img_filme);
        filme = new Filme();
    }

    public Filme getFilme(){
        filme.setTitulo(txtTitulo.getText().toString());
        filme.setDataLancamento(txtDate.getText().toString());
        filme.setDiretor(txtDiretor.getText().toString());
        filme.setDuracao(txtDuracao.getText().toString());
        filme.setGenero(txtGenero.getText().toString());
        filme.setNota(nota.getProgress());

//       ↓ TRANSFORMA IMAGEM DE IMAGEVIEW PARA BITMAP ↓
        Bitmap bitmap = ((BitmapDrawable) imgFilme.getDrawable()).getBitmap();
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

//        CONVERTER O BITMAP EM UM BYTEARRAY
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0,byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();

        filme.setFoto(fotoArray);

        return filme;
    }

    public void preencherFormulario(Filme filme){
        txtTitulo.setText(filme.getTitulo());
        txtDiretor.setText(filme.getDiretor());
        txtDate.setText(filme.getDataLancamento());
        txtDuracao.setText(filme.getDuracao());
        txtGenero.setText(filme.getGenero());
        nota.setProgress(filme.getNota());

//        TRANFORMAR O BYTEARRAY EM BITMAP
        if(filme.getFoto() != null){
            imgFilme.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));
        }
        this.filme = filme;
    }
}






