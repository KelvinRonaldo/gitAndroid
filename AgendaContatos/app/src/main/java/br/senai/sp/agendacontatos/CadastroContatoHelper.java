package br.senai.sp.agendacontatos;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import br.senai.sp.modelo.Contato;
import br.senai.sp.utils.ValidacaoLetraNum;

public class CadastroContatoHelper {
    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtLinkedin;

    private TextInputLayout layoutNome;
    private TextInputLayout layoutEndereco;
    private TextInputLayout layoutTelefone;
    private TextInputLayout layoutEmail;
    private TextInputLayout layoutLinkedin;

    private Contato contato;

    public CadastroContatoHelper(CadastroContatos activity){
        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtLinkedin = activity.findViewById(R.id.txt_linkedin);

        layoutNome =  activity.findViewById(R.id.layout_txt_nome);
        layoutEndereco =  activity.findViewById(R.id.layout_txt_endereco);
        layoutTelefone =  activity.findViewById(R.id.layout_txt_telefone);
        layoutEmail =  activity.findViewById(R.id.layout_txt_email);
        layoutLinkedin =  activity.findViewById(R.id.layout_txt_linkedin);

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
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());
        txtTelefone.setText(contato.getTelefone());
        txtEmail.setText(contato.getEmail());
        txtLinkedin.setText(contato.getLinkedin());
        this.contato = contato;
    }

    public boolean validarVazio(Context activity){
        boolean vazioValidado = true;
        String vazio = "Preencha este campos para fazer o cadastro!";

        if(txtNome.getText().toString().isEmpty()){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError(vazio);
            vazioValidado = false;
        }else{
            layoutNome.setErrorEnabled(false);
        }

        if(txtEndereco.getText().toString().isEmpty()){
            layoutEndereco.setErrorEnabled(true);
            layoutEndereco.setError(vazio);
            vazioValidado = false;
        }else{
            layoutEndereco.setErrorEnabled(false);
        }

        if(txtTelefone.getText().toString().isEmpty()){
            layoutTelefone.setErrorEnabled(true);
            layoutTelefone.setError(vazio);
            vazioValidado = false;
        }else{
            layoutTelefone.setErrorEnabled(false);
        }

        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError(vazio);
            vazioValidado = false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(txtLinkedin.getText().toString().isEmpty()){
            layoutLinkedin.setErrorEnabled(true);
            layoutLinkedin.setError(vazio);
            vazioValidado = false;
        }else{
            layoutLinkedin.setErrorEnabled(false);
        }

        return vazioValidado;
    }


    public boolean validarCaracter(Context activity){
        boolean caracterValidado = true;
        String invalido = "Caractére(s) inválido(s) inserido(s) neste campo!";

        ValidacaoLetraNum validacao = new ValidacaoLetraNum();

        String nome = txtNome.getText().toString();
        String telefone = txtTelefone.getText().toString();
        String endereco = txtEndereco.getText().toString();
        String email = txtEmail.getText().toString();

        if(validacao.verificarLetra(activity, nome)){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError(invalido);
            caracterValidado = false;
        }else{
            layoutNome.setErrorEnabled(false);
        }

        if(!endereco.matches("[à-úÀ-Úa-zA-Z0-9-.,º ]*")){
            layoutEndereco.setErrorEnabled(true);
            layoutEndereco.setError(invalido);
            caracterValidado = false;
        }else{
            layoutEndereco.setErrorEnabled(false);
        }

        if(!email.matches("[0-9a-zA-Z._-]+@[a-z]+([.][a-z]{2,})+")){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Insira um e-mail válido");
            caracterValidado = false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(!telefone.matches("[0-9]{2}[ ]?9?[0-9]{4}-?[0-9]{4}")){
            layoutTelefone.setErrorEnabled(true);
            layoutTelefone.setError("O Telefone inserido não é valido, siga o exemplo acima!");
            caracterValidado = false;
        }else{
            layoutTelefone.setErrorEnabled(false);
        }

        return caracterValidado;
    }
}












