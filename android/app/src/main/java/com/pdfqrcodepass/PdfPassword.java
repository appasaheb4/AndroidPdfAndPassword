package com.pdfqrcodepass;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Canvas;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;

import android.os.Environment;
import android.os.Bundle;
import android.graphics.Bitmap;

public class PdfPassword extends ReactContextBaseJavaModule {
    int width, height;
    Bitmap bitmap1,bitmap2;

    public PdfPassword(ReactApplicationContext reactContext) {
        super(reactContext); // required by React Native
    }

    @Override
    // getName is required to define the name of the module represented in
    // JavaScript
    public String getName() {
        return "PdfPassword";
    }



    @ReactMethod
    public  void  pdfCreate(String shareTitle,  String pathShare1, Callback errorCallback, Callback successCallback){
        try
        {
            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/pdfFiles/";
            // create a new document
            PdfDocument document = new PdfDocument();
            // crate a page description
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
            // start a page
            PdfDocument.Page page = document.startPage(pageInfo);
            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            canvas.drawText(shareTitle, 20, 10, paint);
            Bitmap bitmap = BitmapFactory.decodeFile(directory_path+pathShare1);
            bitmap = Bitmap.createScaledBitmap(bitmap, 40, 40, false);
            canvas.drawBitmap(bitmap,20,20,paint);

          
            // finish the page
            document.finishPage(page);
            // Create Page 2
            pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();
            paint = new Paint();
            paint.setColor(Color.BLUE);
            canvas.drawCircle(100, 100, 100, paint);
            document.finishPage(page);
            // write the document content

            File file = new File(directory_path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String targetPdf = directory_path+"Share4th.pdf";
            File filePath = new File(targetPdf);
            try {
                document.writeTo(new FileOutputStream(filePath));
                successCallback.invoke( "Done");
            } catch (IOException e) {
                Log.e("main", "error "+e.toString());
               // Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
                successCallback.invoke("Something wrong");
            }
            // close the document
            document.close();
    }catch (Exception e){
            errorCallback.invoke(e.getMessage());
        }
    }

//    @ReactMethod
//    public void setPdfPasswrod(String filePath, String password, Callback errorCallback, Callback successCallback) {
//        try {
//            // File sdcard = Environment.getExternalStorageDirectory();
//            // OutputStream file = new FileOutputStream(new File(sdcard,filePath));
//            // Document document = new Document();
//            // PdfWriter writer = PdfWriter.getInstance(document, file);
//            // writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),
//            // PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//
//            PdfReader reader = new PdfReader(filePath);
//            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(filePath));
//            stamper.setEncryption(USER, OWNER, PdfWriter.ALLOW_PRINTING,
//                    PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
//            stamper.close();
//            reader.close();
//
//            successCallback.invoke("Password set sucess: " + filePath);
//        } catch (Exception e) {
//            errorCallback.invoke(e.getMessage());
//        }
//    }
//
//    @ReactMethod
//    public void encryptPdf(String src, String dest) throws IOException, DocumentException {
//        PdfReader reader = new PdfReader(src);
//        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
//        stamper.setEncryption(USER, OWNER, PdfWriter.ALLOW_PRINTING,
//                PdfWriter.ENCRYPTION_AES_128 | PdfWriter.DO_NOT_ENCRYPT_METADATA);
//        stamper.close();
//        reader.close();
//    }
}