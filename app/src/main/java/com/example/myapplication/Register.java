package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Details.DetailsBasic;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class Register extends Activity {


    CardView register_btn;
    private EditText email,pass,con_pass;
    private String uemail,epass, econ_pass;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private TextView textView ;
    public static final String MyPreference="MyHostel";
    public static final String Email="Email";


//
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.user_email);
        pass=findViewById(R.id.user_pass);
        con_pass=findViewById(R.id.user_con_pass);
        register_btn=findViewById(R.id.user_reg);
        progressBar=findViewById(R.id.p_bar);
        textView=findViewById(R.id.user_reg1);

        sharedPreferences = getSharedPreferences(MyPreference, Context.MODE_PRIVATE);




        register_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                textView.setText("Please Wait...");
                registerUser();



            }
        });


    }

    private void registerUser(){

        uemail=email.getText().toString().trim();
        epass=pass.getText().toString().trim();
        econ_pass=con_pass.getText().toString().trim();

        if(TextUtils.isEmpty(uemail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(epass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(econ_pass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(uemail, epass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString(Email,uemail);
                        editor.commit();
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,"Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), DetailsBasic.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Register.this,"Registration Error : "+task.getResult(),Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    }




}
