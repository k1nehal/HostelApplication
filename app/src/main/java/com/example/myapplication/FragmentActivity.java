package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Student.Complaint;
import com.example.myapplication.Student.Contact_Warden;
import com.example.myapplication.Student.Events;
import com.example.myapplication.Student.Leaves;
import com.example.myapplication.Student.Outings;
import com.example.myapplication.Student.Timings;
import com.example.myapplication.Warden.Complaints;
import com.example.myapplication.Warden.LeaveRequests;
import com.example.myapplication.Warden.New_Student;
import com.example.myapplication.Warden.OutingRequest;
import com.example.myapplication.Warden.Records;
import com.example.myapplication.Warden.Rounds;
import com.example.myapplication.Warden.UpdateEvents;

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



            case "New Students":
                fragment=new New_Student();
                break;
            case "Round Records":
                fragment=new Rounds();
                break;
            case "Leave Requests":
                fragment=new LeaveRequests();
                break;
            case "Outing Requests":
                fragment=new OutingRequest();
                break;
            case "New Event":
                fragment=new UpdateEvents();
                break;
            case "Complaints":
                fragment=new Complaints();
                break;
            case "Records":
                fragment=new Records();
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
