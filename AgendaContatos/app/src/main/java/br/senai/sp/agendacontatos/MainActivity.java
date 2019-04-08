package br.senai.sp.agendacontatos;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.adapter.ContatosAdapter;
import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;
import br.senai.sp.utils.CaixaDeDialogo;

public class MainActivity extends AppCompatActivity {

    private ListView listaContatos;
    private ImageButton botaoAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContatos = findViewById(R.id.lista_contatos);
        botaoAdd = findViewById(R.id.botao_adicionar);

//        String[] contatos = {"Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda","Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda","Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda","Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda","Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda","Kelvin", "Sabrina", "Lohany", "Felipe", "Fernando",
//                "Amanda", "Ananda"};

        registerForContextMenu(listaContatos);

        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroContatos.class);
                startActivity(intent);
            }
        });
//        listaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Contato contato = (Contato) listaContatos.getItemAtPosition(position);
//                Intent abrirCadastro = new Intent(MainActivity.this, CadastroContatos.class);
//                abrirCadastro.putExtra("contato", contato);
//                startActivity(abrirCadastro);
//            }
//        });

//        ArrayAdapter<String> listaContatosAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);
//
//        listaContatos.setAdapter(listaContatosAdapter);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_contexto_lista, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato contato = (Contato) listaContatos.getItemAtPosition(info.position);
        final ContatoDAO dao = new ContatoDAO(MainActivity.this);

        AlertDialog.Builder confirmarExclusao = new AlertDialog.Builder(this);
        confirmarExclusao.setTitle("EXCLUIR CONTATO");
        confirmarExclusao.setMessage("Tem certeza de que deseja excluir " + contato.getNome() + "?");
        confirmarExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(contato);
                Toast.makeText(MainActivity.this, contato.getNome() + "  foi excluído(a)!", Toast.LENGTH_SHORT).show();
                dao.close();
                carregarLista();
            }
        });
        confirmarExclusao.setNegativeButton("Não", null);
        confirmarExclusao.create().show();

//        CaixaDeDialogo d = new CaixaDeDialogo();
//        d.excluirContato(contato, dao, this);

        return super.onContextItemSelected(item);
    }


    @Override
    protected void onResume() {
        carregarLista();
        super.onResume();
    }

    private void carregarLista(){
        ContatoDAO dao = new ContatoDAO(this);
        List<Contato> contatos = dao.getContatos();

//        ArrayAdapter<Contato> listaContatosAdapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        ContatosAdapter adapter = new ContatosAdapter(this, contatos);

        listaContatos.setAdapter(adapter);
    }
}
