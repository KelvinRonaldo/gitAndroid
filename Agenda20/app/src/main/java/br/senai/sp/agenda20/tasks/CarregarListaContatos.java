package br.senai.sp.agenda20.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agenda20.MainActivity;
import br.senai.sp.agenda20.model.Contato;

public class CarregarListaContatos extends AsyncTask {

    private String dados = "";
    private ProgressDialog progressDialog;
    private Context context;
    private ArrayAdapter<Contato> adapter;
    private List<Contato> contatos;

//    O CONTRUTOR É PRIMEIRO MÉTODO A SER EXECUTADO
    public CarregarListaContatos(Context context) {
        this.context = context;
    }

    //    ESTE MÉTODO É O TERCEIRO A SER EXECUTADO
//    MÉTODO OBRIGATORIO EM CLASS QUE HERDAM A CLASSE ASYNC TAKS
    @Override
    protected Object doInBackground(Object[] objects) {

        try {
//            DETERMINAR A URL DO RECURSO A SER UTILIZADO NO WEBSERVICE
            URL url = new URL("http://10.107.144.27:8080/contatos");

//            CRIAR CONEXÃO HTTP COM O WEBSERVICE
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

//            TRATAR O FLUXO DE DADOS QUE ESTÃO CHEGANDO DO SERVIDOR
//            PARA O APP MOBILE
            InputStream dadosStream = conexao.getInputStream();

//            CRIAR UM OBJETO CAPAZ DE LER OS DADOS NO STREAM
//            DE ENTRADA
            InputStreamReader leitorStream = new InputStreamReader(dadosStream);

//            CRIAR UM BUFFER(local na memória para armazenar algo por
//            um curto espaço de tempo)
            BufferedReader bufferedReader = new BufferedReader(leitorStream);

//            CRIAR VARIÁVEL STRING PARA EXTRAIR CONTEÚDO DO BUFFER
            String registro = "";

//            ITERAR O BUFFER E A CADA ITERAÇÃO CARREGAR A VARIÁVEL REGISTRO
            while(registro != null){
                registro = bufferedReader.readLine();
                dados += registro;
            }

//            CRIAR UM ARRAYLIST PARA CARREGAR TODOS OS CONTATOS DO BANCO(API)
            Contato contato;
            contatos = new ArrayList<>();

//            TRANSFORMAR A dados EM ARRAY
            try {
                JSONArray dadosArray = new JSONArray(dados);

//            ITERAR O ARRAY PARA CRIAR OS OBJETOS CONTATO QUE SERÃO INSERIDOS NO ARRAYLIST
                for(int i = 0; i <= dadosArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) dadosArray.get(i);

                    contato = new Contato();
                    contato.setId(jsonObject.getInt("id"));
                    contato.setNome(jsonObject.getString("nome"));
                    contato.setEmail(jsonObject.getString("email"));
                    contato.setEndereço(jsonObject.getString("endereco"));
                    contato.setTelefone(jsonObject.getString("telefone"));
                    contato.setLinkedin(jsonObject.getString("linkedin"));
                    contato.setFoto(jsonObject.getString("foto"));

                    contatos.add(contato);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//    ESTE MÉTODO É O SEGUNDO A SER EXECUTADO
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(this.context);
        progressDialog.setTitle("Pera um minutin");
        progressDialog.setMessage("Ta no loading meu consagrado!");
        progressDialog.show();
    }

    //    ESTE MÉTODO É O QUARTO A SER EXECUTADO
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        adapter = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1, contatos);
        MainActivity.listViewContatos.setAdapter(adapter);
        progressDialog.dismiss();
    }
}
