package br.senai.sp.conversor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Imagem {

    public static Bitmap arrayToBitmap(byte[] imagemArray){
        Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemArray, 0, imagemArray.length);

        return imagemBitmap;
    }

//    public static byte[] imageViewToArray(){
//
//    }
}
