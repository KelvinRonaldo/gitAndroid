package br.senai.sp.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
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
                if(helper.validar()){
                    Contato contato = helper.getContato();
                    ContatoDAO dao = new ContatoDAO(this);

                    if(contato.getId() == 0){
                        dao.salvar(contato);
                        Toast.makeText(this,  contato.getNome() + " gravado(a) com sucesso!", Toast.LENGTH_SHORT).show();
                    }else{
                        dao.atualizar(contato);
                        Toast.makeText(this,  contato.getNome() + "  foi atualizado(a)!", Toast.LENGTH_SHORT).show();
                    }
                    dao.close();
                    finish();
                }else{
                }
                break;
            case R.id.item_excluir:
                final Contato contato = helper.getContato();
                final ContatoDAO dao = new ContatoDAO(this);

                AlertDialog.Builder confirmarExclusao = new AlertDialog.Builder(this);
                confirmarExclusao.setTitle("EXCLUIR CONTATO");
                confirmarExclusao.setMessage("Tem certeza de que deseja excluir " + contato.getNome() + "?");
                confirmarExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dao.excluir(contato);
                        Toast.makeText(CadastroContatos.this, contato.getNome() + "  foi excluído(a)!", Toast.LENGTH_SHORT).show();
                        dao.close();
                        finish();
                    }
                });
                confirmarExclusao.setNegativeButton("Não", null);
                confirmarExclusao.create().show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
