package br.senai.sp.catalogodefilmes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroActivity extends AppCompatActivity {
    private CadastroFilmeHelper helper;
    private Button btnCamera;
    private Button btnGaleria;
    private ImageView imgFilme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);


        btnCamera = findViewById(R.id.btn_camera);
        btnGaleria = findViewById(R.id.btn_galeria);
        imgFilme = findViewById(R.id.img_filme);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
                intentGaleria.setType("image/*");
                startActivityForResult(intentGaleria, 1);
            }
        });



        helper = new CadastroFilmeHelper(this);

//        pegando a intent
        Intent intent = getIntent();
//                                pegando elemento que foi mandado pela intent
        Filme filme = (Filme) intent.getSerializableExtra("filme");

        if(filme == null){
        }
        else{
            helper.preencherFormulario(filme);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        InputStream inputStream = null;
        try {

            inputStream = getContentResolver().openInputStream(data.getData());
            Bitmap bitmapFactory = BitmapFactory.decodeStream(inputStream);
            imgFilme.setImageBitmap(bitmapFactory);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //A Classe 'MenuInflater' pega o menu que esta na activity, 'infla' ele com o novo menu que foi criado e retorna o mesmo
        //Este método é chamado quando a classe se inicia em 'super.onCreate(savedInstanceState)'
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_filmes, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Este metodo retorna o item que foi clicado e depois usamos uma condição para verificar qual foi o botão

        switch (item.getItemId()){
            case R.id.menu_salvar:
                Filme filme = helper.getFilme();

                FilmeDAO dao = new FilmeDAO(this);

                if(filme.getId() == 0){
                    dao.salvar(filme);
                    Toast.makeText(this, filme.getTitulo() +" Gravado com Sucesso!", Toast.LENGTH_LONG).show();
                }else{
                    dao.atualizar(filme);
                    Toast.makeText(this, filme.getTitulo() +" Atualizado com Sucesso!", Toast.LENGTH_LONG).show();
                }

                dao.close();

                finish();

                break;
            case R.id.menu_del:
                Toast.makeText(CadastroActivity.this, "Deletar", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_configuracoes:
                Toast.makeText(CadastroActivity.this, "Configurações", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(CadastroActivity.this, "Nada", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}