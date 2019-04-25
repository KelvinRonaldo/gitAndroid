package br.senai.sp.agenda20.utils;

import org.json.JSONException;
import org.json.JSONStringer;

import br.senai.sp.agenda20.model.Contato;

public class CreateContatoJson {

    private Contato contato;

    public CreateContatoJson(Contato contato) {
        this.contato = contato;
    }
    public JSONStringer criarJson(){
        JSONStringer jsonContato = new JSONStringer();

        try {
            jsonContato.object(); //INICIANDO O JSON = '{'
            jsonContato.key("nome").value(contato.getNome());
            jsonContato.key("email").value(contato.getEmail());
            jsonContato.key("endereco").value(contato.getEndereço());
            jsonContato.key("telefone").value(contato.getTelefone());
            jsonContato.key("linkedin").value(contato.getLinkedin());
            jsonContato.key("foto").value(contato.getFoto());
            jsonContato.endObject(); //FINALIZA O JSON = '}'

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonContato;
    }
}
