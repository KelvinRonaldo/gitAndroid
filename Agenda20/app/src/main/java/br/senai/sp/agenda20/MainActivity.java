package br.senai.sp.agenda20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import br.senai.sp.agenda20.tasks.CarregarListaContatos;

public class MainActivity extends AppCompatActivity {

    public static ListView listViewContatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewContatos = findViewById(R.id.main_lista_contatos);


    }

    @Override
    protected void onResume() {
        super.onResume();

        CarregarListaContatos carregarListaContatos = new CarregarListaContatos(this);
        carregarListaContatos.execute();
    }
}
