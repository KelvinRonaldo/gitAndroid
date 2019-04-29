package br.senai.sp.agenda20.tasks;

import android.os.AsyncTask;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agenda20.model.Contato;
import br.senai.sp.agenda20.utils.CreateContatoJson;

public class GravarContato extends AsyncTask {

    private Contato contato;

    public GravarContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

//        JSONStringer jsonContato = new JSONStringer();
        try {
//            jsonContato.object(); //INICIANDO O JSON = '{'
//            jsonContato.key("nome").value(contato.getNome());
//            jsonContato.key("email").value(contato.getEmail());
//            jsonContato.key("endereco").value(contato.getEndereço());
//            jsonContato.key("telefone").value(contato.getTelefone());
//            jsonContato.key("linkedin").value(contato.getLinkedin());
//            jsonContato.key("foto").value(contato.getFoto());
//            jsonContato.endObject(); //FINALIZA O JSON = '}'

            CreateContatoJson jsContato = new CreateContatoJson(contato);
            JSONStringer jsonContato = jsContato.criarJson("gravar");


            URL url = new URL("http://10.107.134.8:8080/contatos");
//            CRIANDO CONEXAO PARA A URL
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

//            DIZENDO O TIPO DE DO CONTEÚDO DA REQUISICAO
            conexao.setRequestProperty("Content-type", "application/json");

//            DIZENDO QUE ACEITA O RETORNO DA REQUISICAO EM JSON
            conexao.setRequestProperty("Accept", "application/json");

//            DIZENDO O MÉTODO DA REQUISICAO
            conexao.setRequestMethod("POST");

//            DIZENDO QUE VOU "FAZER" UMA ENTRADA NO SERVIDOR A PARTIR DA CONEXAO
            conexao.setDoInput(true);

//            CRIANDO O FLUXO DE SAÍDA DE DADOS PELA CONEXAO LEVANDO O JSON
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsonContato);

//            FAZENDO A CONEXAO
            conexao.connect();

            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();

//        } catch (JSONException e) {
//            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
