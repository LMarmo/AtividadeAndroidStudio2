package com.marmo.orcamento;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marmo.orcamento.modelo.Orcamento;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class novoOrcamento extends AppCompatActivity{

    //codigo para utilizar a camera
    private String currentPhotoPath;
    //codigo para utilizar a camera
    private static final int REQUEST_TAKE_PHOTO = 105;

    private int foto = 0;

    ImageView imageView;
    private DatabaseReference databaseReference;

    Orcamento orcamento = new Orcamento();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_orcamento);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        imageView = (ImageView) findViewById(R.id.imageView);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,  Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//        }


        imageView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                imageView.setImageBitmap(null);
                imageView.setBackground(getResources().getDrawable(R.drawable.ic_baseline_photo_camera_24));
                orcamento.setImagem("");
                return true;
            }

        });

    }




    //codigo para utilizar a camera
    public File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //codigo para utilizar a camera
    public void chamarCamera(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.marmo.orcamento",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
    public void chamarCamera1(View view){
        foto=1;
        chamarCamera(view);

    }
    public void vozTextoDescricao(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Qual o Titulo?");

        try {
            startActivityForResult(intent, 101);
        } catch (ActivityNotFoundException a) {
            Log.e("Orcamento", "Activity não Encontrada");
        }


    }
    //        metodo que sempre vai ser chamado quando tiver retorno de alguma activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 101: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> resultado = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    EditText editText = (EditText) findViewById(R.id.inputDescricao);
                    editText.setText(editText.getText().toString() + " " + resultado.get(0));

                }
                break;
            }
            case 105:{
                if(resultCode == RESULT_OK){
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = 3;
                        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, options);

                        //converter o bitmap em base64 (String), que é util para mandar a foto para um webservice
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                        byte[] binario = outputStream.toByteArray();
                        String fotoString = Base64.encodeToString(binario, Base64.DEFAULT);
                        Log.i("Orcamento", "" + fotoString.length());

                        if (foto == 1) {

                            imageView.setImageBitmap(bitmap);
                            imageView.setBackground(null);
                            orcamento.setImagem(currentPhotoPath);
                        }
                    }catch (Exception i){
                        Log.e("Orcamento", "Deu Erro!!!");
                    }

                }
                break;
            }
        }
    }





    public void salvarOrcamento(View view) {
        EditText editCliente = (EditText) findViewById(R.id.inputCliente);
        EditText editCPFCNPJ = (EditText) findViewById(R.id.inputCpfCnpj);
        EditText editDescricao = (EditText) findViewById(R.id.inputDescricao);
        EditText editTelefone = (EditText) findViewById(R.id.inputTelefone);
        EditText editValor = (EditText) findViewById(R.id.inputValor);


        orcamento.setCliente(editCliente.getText().toString());
        orcamento.setCpfcnpj(editCPFCNPJ.getText().toString());
        orcamento.setDescricao(editDescricao.getText().toString());
        orcamento.setTelefone(editTelefone.getText().toString());
        orcamento.setValorOrcamento(Double.parseDouble(editValor.getText().toString()));



        orcamento.save();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String chave = databaseReference.child(user.getUid()).child("Memoria").push().getKey();
        orcamento.setChave(chave);
        databaseReference.child(user.getUid()).child("Memoria").child(chave).setValue(orcamento);


//      Toast.makeText(this, memoriaAtividade.getLatitude()+" "+memoriaAtividade.getLongitude(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "A Cliente Armazenado com Sucesso!!", Toast.LENGTH_LONG).show();
        finish();
    }


}