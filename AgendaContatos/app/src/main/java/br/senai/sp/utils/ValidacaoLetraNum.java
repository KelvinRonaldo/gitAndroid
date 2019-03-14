package br.senai.sp.utils;

import android.content.Context;
import android.widget.Toast;

public class ValidacaoLetraNum {

    public boolean verificarLetra(Context activity, String elemento){


       int numeros = 0;
        for(int cont = 0; cont < elemento.length(); cont++){
            if(Character.isLetter(elemento.charAt(cont)) || Character.isSpaceChar(elemento.charAt(cont))){

            }else{
                numeros++;
            }
        }
        if(numeros != 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean verificarDigito(Context activity, String elemento){


        int letras = 0;
        for(int cont = 0; cont < elemento.length(); cont++){
            if(Character.isDigit(elemento.charAt(cont))){

            }else{
                letras++;
            }
        }
        if(letras != 0){
            return true;
        }else{
            return false;
        }

    }
}
