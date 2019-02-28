package br.senai.sp.agendacontatos;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.senai.sp.modelo.Contato;

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
    public boolean validar(){
        boolean validado = true;
        String vazio = "Preencha este campos para fazer o cadastro!";

        if(txtNome.getText().toString().isEmpty()){
            layoutNome.setErrorEnabled(true);
            layoutNome.setError(vazio);
            validado = false;
        }else{
            layoutNome.setErrorEnabled(false);
        }

        if(txtEndereco.getText().toString().isEmpty()){
            layoutEndereco.setErrorEnabled(true);
            layoutEndereco.setError(vazio);
            validado = false;
        }else{
            layoutEndereco.setErrorEnabled(false);
        }

        if(txtTelefone.getText().toString().isEmpty()){
            layoutTelefone.setErrorEnabled(true);
            layoutTelefone.setError(vazio);
            validado = false;
        }else{
            layoutTelefone.setErrorEnabled(false);
        }

        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError(vazio);
            validado = false;
        }else{
            layoutEmail.setErrorEnabled(false);
        }

        if(txtLinkedin.getText().toString().isEmpty()){
            layoutLinkedin.setErrorEnabled(true);
            layoutLinkedin.setError(vazio);
            validado = false;
        }else{
            layoutLinkedin.setErrorEnabled(false);
        }
//        if(txtNome.getText().toString().){
//
//        }


        return validado;
    }


}












