package br.senai.sp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.catalogodefilmes.R;
import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Filme;

public class FilmesAdapter extends BaseAdapter {

    private List<Filme> filmes;
    private Context context;

    public FilmesAdapter(Context context, List<Filme> filmes){
        this.filmes = filmes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return filmes.size();
    }

//    RETORNA O OBJETO NA POSIÇÃO CLICADA
    @Override
    public Object getItem(int position) {
        return filmes.get(position);
    }

//    RETORNA A POSIÇÃO CLICADA NA LISTA
    @Override
    public long getItemId(int position) {
        return filmes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        cliando um filme na classe a atrinuindo a ele o filme da posicao clicada da lista
        Filme filme = filmes.get(position);

//        inflando o layout na activity chamada
        LayoutInflater inflater = LayoutInflater.from(context);
//        inflando o layout criado em uma view
        View view = inflater.inflate(R.layout.layout_lista_filmes, null);

        TextView txtTitulo = view.findViewById(R.id.txt_titulo);
        TextView txtDiretor = view.findViewById(R.id.txt_diretor);
        RatingBar rtNota = view.findViewById(R.id.rate_nota);
        ImageView imgFilme = view.findViewById(R.id.image_view);

        txtTitulo.setText(filme.getTitulo());
        txtDiretor.setText(filme.getDiretor());
        rtNota.setProgress(filme.getNota());
        rtNota.setEnabled(false);


        imgFilme.setImageBitmap(Imagem.arrayToBitmap(filme.getFoto()));

        return view;
    }
}
