package br.senai.sp.agenda20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.CopyOnWriteArrayList;

import br.senai.sp.agenda20.model.Contato;
import br.senai.sp.agenda20.tasks.GravarContato;

public class CadastroContato extends AppCompatActivity {

    private Button btnGravar;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtLinkedin;
    private EditText txtFoto;

    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        btnGravar = findViewById(R.id.btn_novo_contato);
        txtNome = findViewById(R.id.txt_nome);
        txtEmail = findViewById(R.id.txt_email);
        txtEndereco = findViewById(R.id.txt_endereco);
        txtTelefone = findViewById(R.id.txt_telefone);
        txtLinkedin = findViewById(R.id.txt_linkedin);
        txtFoto = findViewById(R.id.txt_foto);



        btnGravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contato contato = new Contato();
                contato.setNome(txtNome.getText().toString());
                contato.setEmail(txtEmail.getText().toString());
                contato.setEndereço(txtEndereco.getText().toString());
                contato.setTelefone(txtTelefone.getText().toString());
                contato.setLinkedin(txtLinkedin.getText().toString());
                contato.setFoto(txtFoto.getText().toString());

                GravarContato gravarContato = new GravarContato(contato);
                gravarContato.execute();
            }
        });


        Intent intent = getIntent();
        Contato contato = (Contato) intent.getSerializableExtra("contato");

        if(contato != null){
            this.contato = contato;
            txtNome.setText(contato.getNome().toString());
            txtEmail.setText(contato.getEmail().toString());
            txtEndereco.setText(contato.getEndereço().toString());
            txtTelefone.setText(contato.getTelefone().toString());
            txtLinkedin.setText(contato.getLinkedin().toString());
            txtFoto.setText(contato.getFoto().toString());
        }else{
            Toast.makeText(this, "Nulo", Toast.LENGTH_SHORT).show();
        }


    }
}
