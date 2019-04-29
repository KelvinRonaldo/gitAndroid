package br.senai.sp.agenda20.tasks;

import android.os.AsyncTask;

import org.json.JSONStringer;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agenda20.model.Contato;
import br.senai.sp.agenda20.utils.CreateContatoJson;

public class AtualizarContato extends AsyncTask{

    private Contato contato;

    public AtualizarContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    protected Object doInBackground(Object[] objects) {


        try {
            CreateContatoJson jsContato = new CreateContatoJson(contato);
            JSONStringer jsonContato = jsContato.criarJson("atualizar");

            URL url = new URL("http://10.107.134.8:8080/contatos/"+contato.getId());
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setRequestProperty("Accept", "application/json");
            conexao.setRequestMethod("PUT");
            conexao.setDoInput(true);
            PrintStream output = new PrintStream(conexao.getOutputStream());
            output.print(jsonContato);
            conexao.connect();
            Scanner scanner = new Scanner(conexao.getInputStream());
            String resposta = scanner.nextLine();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}


















