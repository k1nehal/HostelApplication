package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;


public class MainActivity extends Activity {
RadioButton radioButton;
Boolean aBoolean;
TextView register;
EditText email,password;
FirebaseAuth firebaseAuth;
TextView forgot;
SharedPreferences sharedPreferences;
    public static final String MyPreference="MyHostel";
    public static final String Email="Email";
    public static final String Approved="Approved";



    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("MyHostel", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);

        forgot = findViewById(R.id.forgot);


        firebaseAuth = FirebaseAuth.getInstance();
        try {
            if(firebaseAuth.getCurrentUser()!=null)
            {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                finish();
            }
        } catch (Exception e){
//            startActivity(new Intent(MainActivity.this,));
        }

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,WardenDashboard.class);
                startActivity(intent1);
            }
        });
        radioButton=findViewById(R.id.student_radio);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.password);

        firebaseAuth=FirebaseAuth.getInstance();



        aBoolean=radioButton.isChecked();
        register=findViewById(R.id.register);
        CardView cardView=findViewById(R.id.cv);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String u_email=email.getText().toString();
                String u_pass=password.getText().toString();

                if(TextUtils.isEmpty(u_email))
                {
                    Toast.makeText(getApplicationContext(),"Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(u_pass))
                {
                    Toast.makeText(getApplicationContext(),"Pleas enter password",Toast.LENGTH_SHORT).show();
                    return;
                }



                firebaseAuth.signInWithEmailAndPassword(u_email,u_pass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(MainActivity.this,"Enter your Email or Password",Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString(Email,u_email);
                                    editor.putBoolean(Approved,false);
                                    editor.commit();
                                    Intent intent1=new Intent(MainActivity.this,Main2Activity.class);
                                    startActivity(intent1);
                                    finish();
                                }
                            }
                        });

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        }
    }
