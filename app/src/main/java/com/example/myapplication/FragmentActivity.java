package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentActivity extends androidx.fragment.app.FragmentActivity {
ImageButton imageButton;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        String title=getIntent().getStringExtra("Item");
        imageButton=findViewById(R.id.bckbtn_img);
        textView=findViewById(R.id.eee);
//        Toast.makeText(this,title,Toast.LENGTH_SHORT).show();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Fragment fragment=null;

        switch(title)
        {
            case "Night Attendance":
                fragment=new Complaint();
                break;
            case "Complaint":
                fragment=new Complaint();
                break;
            case "Outings":
                fragment=new Outings();
                break;
            case "Leaves":
                fragment=new Leaves();
                break;
            case "Contacts":
                fragment=new Contact_Warden();
                break;
            case "Timings":
                fragment=new Timings();
                break;
            case "Events":
                fragment=new Events();
                break;

            default:
                startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                Toast.makeText(this,"Please select valid option",Toast.LENGTH_SHORT).show();
                break;
        }
        textView.setText(title+"");
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.commit();
    }

}