package com.jiffy.foodie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText emailText,passwordText;
    String email,password;
    Button loginBtn;
    TextView registerTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        emailText = (EditText)findViewById(R.id.emailText);
        passwordText = (EditText)findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginButton);
        registerTxt = findViewById(R.id.registerText);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                Log.e("Data",email);
                Log.e("Data",password);
                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
                                    if(mAuth.getCurrentUser() != null){
                                        startActivity(new Intent(MainActivity.this,User_Dashboard.class));
                                    }
                                }
                                else{
                                    Toast.makeText(MainActivity.this,"Signin Failed",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterUser.class));
            }
        });

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this,User_Dashboard.class));
        }
    }




}
