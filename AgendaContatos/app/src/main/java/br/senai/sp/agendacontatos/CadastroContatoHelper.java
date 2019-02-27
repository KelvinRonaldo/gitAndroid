package br.senai.sp.agendacontatos;

import android.widget.EditText;

import br.senai.sp.modelo.Contato;

public class CadastroContatoHelper {
    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;
    private Contato contato;

    public CadastroContatoHelper(CadastroContatos activity){
        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);
        contato = new Contato();
    }

    public Contato getContato(){
        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setLinkedin(txtLinkedin.getText().toString());

        return contato;
    }

    public void preencherCampos(Contato contato){
        txtNome.setText(contato.getNome().toString());
        txtEndereco.setText(contato.getEndereco().toString());
        txtTelefone.setText(contato.getTelefone().toString());
        txtEmail.setText(contato.getEmail().toString());
        txtLinkedin.setText(contato.getLinkedin().toString());
        this.contato = contato;
    }


}












