package br.senai.sp.catalogodefilmes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import br.senai.sp.dao.FilmeDAO;
import br.senai.sp.modelo.Filme;

public class CadastroActivity extends AppCompatActivity {

    public static final int GALERIA_REQUEST = 1;
    public static final int CAMERA_REQUEST = 2;
    private CadastroFilmeHelper helper;
    private ImageButton btnCamera;
    private ImageButton btnGaleria;
    private ImageView imgFilme;
    private String caminhoFoto;



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
//                INTENT DE ABRIR A CAMERA
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                                                            ↑ação de abrir a camera
//                DANDO NOME AO ARQUIVO GERADO PELO INTENT
                String nomeArquivo = "/IMG_" + System.currentTimeMillis() + ".jpg";
                caminhoFoto = getExternalFilesDir(null) + nomeArquivo;
                Log.d("TEMPO", nomeArquivo);
                File fileFoto = new File(caminhoFoto);


                Uri fotoUri = FileProvider.getUriForFile(
                        CadastroActivity.this,
                        BuildConfig.APPLICATION_ID + ".provider",
                        fileFoto
                );
//                PENDURANDO NA INTENT UMA SAIDA, QUE É O ARQUIVO GERADO PELA CAMERA
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);

                startActivityForResult(intentCamera, CAMERA_REQUEST);
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                INTENT DE ABRIR A GALERIA
                Intent intentGaleria = new Intent(Intent.ACTION_GET_CONTENT);
//                                                            ↑ação de pegar conteudo
//                DIZENDO QUAL O TIPO DE ARQUIVO QUE VIRÁ DA INTENT
                intentGaleria.setType("image/*");
//                                        ↑tipo do arquivo que será buscado
//                MANDANDO O RESULTADO DA REQUISIÇÃO PARA O MÉTODO onActivityResult()
                startActivityForResult(intentGaleria, GALERIA_REQUEST);
//                                        ↑intent            ↑codigo de requisição
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

        if(resultCode == RESULT_OK){
            try {
                switch (requestCode){
                    case GALERIA_REQUEST:
                        Log.d("GALERIA", String.valueOf(resultCode));

//                        pegando os bits do dado trazidos pelo clique no botao galeria
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());

//                        transformando bits em bitmap
                        Bitmap bitmapGaleria = BitmapFactory.decodeStream(inputStream);

//                        reduzindo dimensoes do bitmap
                        Bitmap bmGaleriaReduzido = Bitmap.createScaledBitmap(bitmapGaleria, 300, 300, true);

//                        colocando imagem bitmap na image view da activity
                        imgFilme.setImageBitmap(bmGaleriaReduzido);

                        break;
                    case CAMERA_REQUEST:
//                        transformando File em bitmap
                        Bitmap bitmapCamera = BitmapFactory.decodeFile(caminhoFoto);
//                        reduzindo dimensoes do bitmap
                        Bitmap bmCameraReduzido = Bitmap.createScaledBitmap(bitmapCamera, 300, 300, true);
//                        colocando imagem bitmap na image view da activity
                        imgFilme.setImageBitmap(bmCameraReduzido);
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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