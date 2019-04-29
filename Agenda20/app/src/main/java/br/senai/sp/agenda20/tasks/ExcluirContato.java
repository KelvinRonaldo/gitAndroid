package br.senai.sp.agenda20.tasks;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.senai.sp.agenda20.model.Contato;

public class ExcluirContato extends AsyncTask{

    private int id;

    public ExcluirContato(int id) {
        this.id =  id;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try{
            URL url = new URL("http://10.107.134.8:8080/contatos/"+id);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("DELETE");
            conexao.connect();
            conexao.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
