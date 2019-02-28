package br.senai.sp.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import br.senai.sp.agendacontatos.MainActivity;
import br.senai.sp.dao.ContatoDAO;
import br.senai.sp.modelo.Contato;

public class CaixaDeDialogo {

//    public CaixaDeDialogo(Contato contato, ContatoDAO dao, MainActivity mainActivity) {
//
//    }

    public void excluirContato(final Contato contato, final ContatoDAO dao, final Context activity){
        AlertDialog.Builder confirmarExclusao = new AlertDialog.Builder(activity);
        confirmarExclusao.setTitle("EXCLUIR CONTATO");
        confirmarExclusao.setMessage("Tem certeza de que deseja excluir " + contato.getNome() + "?");
        confirmarExclusao.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.excluir(contato);
                Toast.makeText(activity, contato.getNome() + "  foi excluído(a)!", Toast.LENGTH_SHORT).show();
                dao.close();
            }
        });
        confirmarExclusao.setNegativeButton("Não", null);
        confirmarExclusao.create().show();

    }

}
