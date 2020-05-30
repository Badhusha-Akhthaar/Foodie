package com.jiffy.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {
    EditText emailText,nameText,phoneText,passwordText;
    Button registerBtn;
    String email,name,phone,password;
    Map<String,String > userData;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    RadioGroup radioButtonGender;
    RadioButton maleR,femaleR;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        emailText = findViewById(R.id.emailText);
        nameText = findViewById(R.id.nameText);
        phoneText = findViewById(R.id.phoneText);
        passwordText = findViewById(R.id.passwordText);
        registerBtn = findViewById(R.id.registerButton);
        radioButtonGender = findViewById(R.id.genderRBtnGrp);
        maleR = findViewById(R.id.genderRBtnM);
        femaleR = findViewById(R.id.genderRBtnF);
        radioButtonGender.clearCheck();
        radioButtonGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                switch (checkedId){
                    case R.id.genderRBtnM:
                        maleR.setChecked(true);
                        femaleR.setChecked(false);
                        break;
                    case R.id.genderRBtnF:
                        maleR.setChecked(false);
                        femaleR.setChecked(true);
                        break;
                }
            }
        });
        mAuth = FirebaseAuth.getInstance();
        final DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference("Users");
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                name = nameText.getText().toString();
                phone = phoneText.getText().toString();
                password = passwordText.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    userData = new HashMap<String, String>();
                                    userData.put("emailID",email);
                                    userData.put("userName",name);
                                    userData.put("phoneNumber",phone);
                                    firebaseDatabase.child(firebaseUser.getUid()).setValue(userData, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                            if(databaseError != null){
                                                Toast.makeText(RegisterUser.this,"Data not saved",Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(RegisterUser.this,"Data saved",Toast.LENGTH_SHORT).show();
                                                if (mAuth.getCurrentUser() != null){
                                                    startActivity(new Intent(RegisterUser.this,MainActivity.class));
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        });

            }
        });
//
//        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser() != null){
//                    Toast.makeText(RegisterUser.this,"User Logged In",Toast.LENGTH_SHORT).show();
//                }
//            }
//        };

    }
}
