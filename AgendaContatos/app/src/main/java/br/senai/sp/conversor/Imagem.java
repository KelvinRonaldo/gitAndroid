package br.senai.sp.conversor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Imagem {

    public static Bitmap arrayToBitmap(byte[] imagemArray){
        Bitmap bm = BitmapFactory.decodeByteArray(imagemArray, 0, imagemArray.length);

        return bm;
    }

    public static byte[] imageViewToArray(ImageView fotoContato){

//       ↓ TRANSFORMA IMAGEM DE IMAGEVIEW PARA BITMAP ↓
        Bitmap bitmap = ((BitmapDrawable) fotoContato.getDrawable()).getBitmap();
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 350, 300, true);

//        CONVERTER O BITMAP EM UM BYTEARRAY
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0,byteArrayOutputStream);
        byte[] fotoArray = byteArrayOutputStream.toByteArray();

        return fotoArray;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int orientation) {

        Matrix matrix = new Matrix();
        switch (orientation) {
            case ExifInterface.ORIENTATION_NORMAL:
                return bitmap;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.setScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.setRotate(180);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.setRotate(90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.setRotate(-90);
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.setRotate(-90);
                break;
            default:
                return bitmap;
        }
        try {
            Bitmap bmRotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            bitmap.recycle();
            return bmRotated;
        }
        catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
