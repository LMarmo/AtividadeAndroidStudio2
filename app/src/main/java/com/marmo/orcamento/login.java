package com.marmo.orcamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void chamarRecuperarSenha(View view){
        Intent intent = new Intent (this,recuperarSenha.class);
        startActivity(intent);

    }
    private void chamarPrincipal(FirebaseUser firebaseUser){

        if(firebaseUser != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
    }
    public void cadastrar (View view){
        Intent it = new Intent(this,novoUsuario.class);
        startActivity(it);
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        chamarPrincipal(currentUser);

    }

    public void login(View view){
        EditText editText1 = (EditText) findViewById(R.id.inputEmail);
        EditText editText2 = (EditText) findViewById(R.id.inputSenha);

        mAuth.signInWithEmailAndPassword(editText1.getText().toString(), editText2.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           // Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            chamarPrincipal(user);
                        } else {
                            // If sign in fails, display a message to the user.
                          //  Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Deu erro, Tente Novamente",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

}