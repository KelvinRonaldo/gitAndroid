package br.senai.sp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.senai.sp.agendacontatos.R;
import br.senai.sp.conversor.Imagem;
import br.senai.sp.modelo.Contato;

public class ContatosAdapter extends BaseAdapter{

//    criando contexto(activity) para a classe
    private Context context;
//    criando a lista de contatos
    private List<Contato> contatos;

    public ContatosAdapter(Context context, List<Contato> contatos) {
//        atribuindo contexto da chamado ao contexto da classe
        this.context = context;
//        atribuindo lista de chamado a lista de contatos da classe
        this.contatos = contatos;
    }

    @Override
    public int getCount() {
//        retornando a quantidade de itens que ha na lista de contatos
        return contatos.size();
    }

    @Override
    public Object getItem(int position) {
//        retornando a posicao do item clicado
        return contatos.get(position);
    }

    @Override
    public long getItemId(int position) {
//        retornando o id do item clicado
        return contatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        pegando o contato da posicao clicada
        Contato contato = contatos.get(position);

//        inflando o layout na lista
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_lista_contatos, null);

//        atribuido às variaveis as view do layout
        TextView txtNome = view.findViewById(R.id.txt_lista_nome);
        TextView txtTelefone = view.findViewById(R.id.txt_lista_telefone);
        ImageView imgListaContato = view.findViewById(R.id.img_lista_contato);

//        colocando as informações do contato no layout
        txtNome.setText(contato.getNome());
        txtTelefone.setText(contato.getTelefone());

//        se escolhida uma foto, ela é colocada no imageView da lista
        if(contato.getFotoContato() != null){
            imgListaContato.setImageBitmap(Imagem.arrayToBitmap(contato.getFotoContato()));
        }

        return view;
    }
}