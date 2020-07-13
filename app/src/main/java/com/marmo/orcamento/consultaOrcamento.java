package com.marmo.orcamento;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marmo.orcamento.modelo.Orcamento;

public class consultaOrcamento extends AppCompatActivity {

    Orcamento orcamento = new Orcamento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_orcamento);

        Bundle bundle = getIntent().getExtras();/*utilizada para recuperar valores que vem como parametro*/
        if(bundle!=null && bundle.containsKey("ID_ORCAMENTO")){
            Long id = bundle.getLong("ID_ORCAMENTO");
            Orcamento orcamento = Orcamento.findById(Orcamento.class, id);

            EditText editText = (EditText) findViewById(R.id.inputCliente);
            editText.setText(orcamento.getCliente());

            EditText editText2 = (EditText) findViewById(R.id.inputCpfCnpj);
            editText2.setText(orcamento.getCpfcnpj());

            EditText editText3 = (EditText) findViewById(R.id.inputTelefone);
            editText3.setText(orcamento.getTelefone());

            EditText editText4 = (EditText) findViewById(R.id.inputDescricao);
            editText4.setText(orcamento.getDescricao());

            TextView editText5 = (TextView) findViewById(R.id.textValor);
            editText5.setText(orcamento.getValorOrcamento().toString());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 3;
            if(orcamento.getImagem() != null && orcamento.getImagem().length() > 2){
                Bitmap bitmap = BitmapFactory.decodeFile(orcamento.getImagem(), options);
                ImageView imageView1 = (ImageView) findViewById(R.id.imageView);
                imageView1.setBackground(null);
                imageView1.setImageBitmap(bitmap);
            }



        }


    }

    public void verImagem(View view) {
        Dialog builder = new Dialog(this);
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {

            }
        });
        ImageView imageView = new ImageView(this);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 3;

        Bitmap bitmap = BitmapFactory.decodeFile(orcamento.getImagem(), options);
        imageView.setImageBitmap(bitmap);
        builder.addContentView(imageView, new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        builder.show();

    }


}