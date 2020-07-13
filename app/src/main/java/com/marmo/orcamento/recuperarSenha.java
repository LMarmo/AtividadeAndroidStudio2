package com.marmo.orcamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class recuperarSenha extends AppCompatActivity {


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);
        mAuth = FirebaseAuth.getInstance();
    }

    public void recuperarSenha(View view){
        EditText editText = (EditText) findViewById(R.id.inputEmailRecuperar);
        mAuth.sendPasswordResetEmail(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              if(task.isSuccessful()){
                  Toast.makeText(recuperarSenha.this, "E-mail Enviado!!", Toast.LENGTH_LONG).show();
                  finish();
              }else{
                  Toast.makeText(recuperarSenha.this,"Algo deu Errado, confira o endereço de E-mail", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }

}