package br.senai.sp.agendacontatos;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;
import br.senai.sp.utils.CaixaDeDialogo;

public class CadastroContatos extends AppCompatActivity {
    public static final int GALERIA_REQUEST = 1;
//    public static final int CAMERA_REQUEST;
    private CadastroContatoHelper helper;
    private ImageButton btnCamera, btnGaleria;
    private ImageView imgContato;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contatos);

        btnGaleria = findViewById(R.id.btn_galeria);
        btnCamera = findViewById(R.id.btn_camera);
        imgContato = findViewById(R.id.img_contato);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

            helper = new CadastroContatoHelper(this);

        final Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato == null){

        }else{
            helper.preencherCampos(contato);
        }

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                intentGaleria.setType("image/*");
                startActivityForResult(intentGaleria, GALERIA_REQUEST);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(data.getData());

            Bitmap bitmapGaleria = BitmapFactory.decodeStream(inputStream);
            Bitmap bmGaleriaReduzido = Bitmap.createScaledBitmap(bitmapGaleria, 300, 300, true);

            imgContato.setImageBitmap(bmGaleriaReduzido);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        super.onActivityResult(requestCode, resultCode, data);
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
                if(helper.validarVazio(this) && helper.validarCaracter(this)){
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


                if(contato.getId() == 0){
                    Toast.makeText(CadastroContatos.this, "Este contato não está cadastrado. Não poder ser excluído!", Toast.LENGTH_SHORT).show();
                }else{
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

//                    CaixaDeDialogo d = new CaixaDeDialogo();
//                    if(d.excluirContato(contato, dao, this)){
//                        finish();
//                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
