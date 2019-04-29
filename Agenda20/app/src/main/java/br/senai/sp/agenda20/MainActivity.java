package br.senai.sp.agenda20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import br.senai.sp.agenda20.model.Contato;
import br.senai.sp.agenda20.tasks.CarregarListaContatos;
import br.senai.sp.agenda20.tasks.ExcluirContato;

public class MainActivity extends AppCompatActivity {

    public static ListView listViewContatos;
    private ImageButton btnAddContato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewContatos = findViewById(R.id.main_lista_contatos);
        btnAddContato = findViewById(R.id.btn_add_contato);

        btnAddContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent(MainActivity.this, CadastroContato.class);
                startActivity(intentCadastro);
            }
        });

        listViewContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) listViewContatos.getItemAtPosition(position);
                Intent intentEditar = new Intent(MainActivity.this, CadastroContato.class);
                intentEditar.putExtra("contato", contato);
                startActivity(intentEditar);
            }
        });

        registerForContextMenu(listViewContatos);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        final MenuItem excluir = menu.add("Excluir");

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Contato contato = (Contato) listViewContatos.getItemAtPosition(info.position);
                ExcluirContato excluirContato = new ExcluirContato(contato.getId());
                excluirContato.execute();
                Toast.makeText(MainActivity.this, String.valueOf(contato.getId()), Toast.LENGTH_SHORT).show();
                onResume();
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaContatos carregarListaContatos = new CarregarListaContatos(this);
        carregarListaContatos.execute();
    }
}
