package br.senai.sp.conversor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Imagem {

    public static Bitmap arrayToBitmap(byte[] imagemArray){
        Bitmap bm = BitmapFactory.decodeByteArray(imagemArray, 0, imagemArray.length);

        return bm;
    }

    public static byte[] imageViewToArray(ImageView fotoContato){

//       ↓ TRANSFORMA IMAGEM DE IMAGEVIEW PARA BITMAP ↓
        Bitmap bitmap = ((BitmapDrawable) fotoContato.getDrawable()).getBitmap();
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

//        CONVERTER O BITMAP EM UM BYTEARRAY
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0,byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();

        return fotoArray;
    }
}
