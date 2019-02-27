package br.senai.sp.agendacontatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class CadastroContatos extends AppCompatActivity {
    private CadastroContatoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contatos);
        helper = new CadastroContatoHelper(this);

        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato == null){
            Toast.makeText(this, "Nada", Toast.LENGTH_SHORT).show();
        }else{
            helper.preencherCampos(contato);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro_contatos, menu);;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item_pronto:
                Contato contato = helper.getContato();
                ContatoDAO dao = new ContatoDAO(this);

                if(contato.getId() == 0){
                    dao.salvar(contato);
                    dao.close();
                    Toast.makeText(this, contato.getNome() + " gravado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    dao.atualizar(contato);
                    dao.close();
                    Toast.makeText(this, contato.getNome() + " atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                break;
            case R.id.item_excluir:
                contato = helper.getContato();
                dao = new ContatoDAO(this);
                dao.excluir(contato);
                dao.close();
                finish();

                Toast.makeText(this, contato.getNome() + " excluído com sucesso!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
