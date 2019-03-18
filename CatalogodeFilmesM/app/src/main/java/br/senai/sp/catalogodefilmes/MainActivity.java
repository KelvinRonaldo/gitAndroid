package br.senai.sp.catalogodefilmes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AlertDialogLayout;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.senai.sp.adapter.FilmesAdapter;
import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class MainActivity extends AppCompatActivity {
    private ListView listaFilmes;
    private Button btNovoFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Associo o objeto 'listView' do Java à View do layout
        listaFilmes = findViewById(R.id.lista_filmes);

        btNovoFilme = findViewById(R.id.btn_novo_fime);

        btNovoFilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(abrirCadastro);
            }
        });

        //***** DEFINIÇÃO DE UM MENU DE CONTEXTO À 'listView'
        registerForContextMenu(listaFilmes);
//          ↑ chama o método onCreateContextMenu


        listaFilmes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Filme filme = (Filme) listaFilmes.getItemAtPosition(position);

                Intent abrirCadastro = new Intent(MainActivity.this, CadastroActivity.class);
//                putExtra =  manda elementos entre Activits
                abrirCadastro.putExtra("filme", filme);
                startActivity(abrirCadastro);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_lista_filmes, menu);

//        MenuItem deletar = menu.add("Excluir");
//        MenuItem editar = menu.add("Editar")
//        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Toast.makeText(MainActivity.this, "Deletar", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        });

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        ↓Tornar a variável constante para que ela não se altere de forma alguma
        final Filme filme = (Filme) listaFilmes.getItemAtPosition(info.position);
        final FilmeDAO dao = new FilmeDAO(MainActivity.this);

        AlertDialog.Builder confirmarExclusao = new AlertDialog.Builder(this);
        confirmarExclusao.setTitle("Excluir Filme");
        confirmarExclusao.setMessage("Deseja mesmo excluir "+ filme.getTitulo()+"?");
        confirmarExclusao.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(filme);
                Toast.makeText(MainActivity.this, filme.getTitulo() + " foi Excluído!", Toast.LENGTH_SHORT).show();
                dao.close();
                carregarLista();
            }
        });
        confirmarExclusao.setNegativeButton("Cancelar", null);
        confirmarExclusao.create().show();



        return super.onContextItemSelected(item);
    }



    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    public void carregarLista(){
        FilmeDAO dao = new FilmeDAO(this);
//        ↓MATRIZ DE FILMES QUE SERAO EXIBIDOS NO LISTVIEW
        List<Filme> filmes = dao.getFilmes();
        dao.close();

        // ****** Criar um adapter que carrega o vetor na list view
//        ArrayAdapter<Filme> listaFilmesAdapter =  new ArrayAdapter<Filme>(this, android.R.layout.simple_list_item_1, filmes);

        FilmesAdapter adapter = new FilmesAdapter(this, filmes);

        listaFilmes.setAdapter(adapter);

//        Injetamos o adapter no objeto lisview
//        listaFilmes.setAdapter(listaFilmesAdapter);
    }

    @Override
    protected void onPause() {
//        Toast.makeText(MainActivity.this, "onPause", Toast.LENGTH_LONG).show();
        super.onPause();
    }
}