package com.marmo.orcamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class novoUsuario extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        mAuth = FirebaseAuth.getInstance();
    }
public void voltarLogin(View view){

        finish();
}
    public void cadastrar(View view){
        EditText editText1 = (EditText) findViewById(R.id.inputEmail);
        EditText editText2 = (EditText) findViewById(R.id.inputSenha);

        if(editText2.getText().toString().length()>5) {
            mAuth.createUserWithEmailAndPassword(editText1.getText().toString(), editText2.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(novoUsuario.this, "Conta Criada Com Sucesso",Toast.LENGTH_SHORT).show();

                            } else {
                                // If sign in fails, display a message to the user.
                                //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(novoUsuario.this, "Deu Erro.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(this,"A senha precisa ter 6 Caracteres ou mais",Toast.LENGTH_SHORT).show();
        }

    }
}