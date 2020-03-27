package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

public class DetailsOther extends Activity {
Spinner school,year,semester,room_cat,room_seater;
EditText branch,reg_no;
CardView submit;
RadioGroup chronicg;
ProgressBar progressBar;
TextView textView;
String sschool,syear,ssemester,sbranch,sreg_no,schroinc,room_c,room_s;
private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_other);
        school=findViewById(R.id.School);
        year=findViewById(R.id.Year);
        semester=findViewById(R.id.Sem);
        branch=findViewById(R.id.Branch);
        reg_no=findViewById(R.id.Reg_No);
        submit=findViewById(R.id.Submit);
        chronicg=findViewById(R.id.Chronic);
        progressBar=findViewById(R.id.p_bar);
        textView=findViewById(R.id.textview);
        room_cat=findViewById(R.id.Room);
        room_seater=findViewById(R.id.Room1);


        sharedPreferences=getSharedPreferences("MyHostel",MODE_PRIVATE);
        final String shared_email=sharedPreferences.getString("Email", "");

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Year, R.layout.support_simple_spinner_dropdown_item);
        year.setAdapter(arrayAdapter);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.School, R.layout.support_simple_spinner_dropdown_item);
        school.setAdapter(arrayAdapter1);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.Sem, R.layout.support_simple_spinner_dropdown_item);
        semester.setAdapter(arrayAdapter2);
        ArrayAdapter<CharSequence> arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.Room_Category, R.layout.support_simple_spinner_dropdown_item);
        room_cat.setAdapter(arrayAdapter3);
        ArrayAdapter<CharSequence> arrayAdapter4 = ArrayAdapter.createFromResource(this, R.array.Seater, R.layout.support_simple_spinner_dropdown_item);
        room_seater.setAdapter(arrayAdapter4);


        final Intent intent=getIntent();
        final DetailsBook detailsBook= intent.getParcelableExtra("Book");
        final DetailsBook2 detailsBook2=intent.getParcelableExtra("Book1");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                textView.setText("");
                sschool=school.getSelectedItem().toString();
                syear=year.getSelectedItem().toString();
                ssemester=semester.getSelectedItem().toString();
                sbranch=branch.getText().toString();
                sreg_no=reg_no.getText().toString();
                room_c=room_cat.getSelectedItem().toString();
                room_s=room_seater.getSelectedItem().toString();
                schroinc =((RadioButton)findViewById(chronicg.getCheckedRadioButtonId())).getText().toString();

                Map<String,Object> note=new HashMap<>();
                note.put("First Name",detailsBook.getFname());
                note.put("Last Name",detailsBook.getLname());
                note.put("Poornima Email",detailsBook.getPemail());
                note.put("Blood Group",detailsBook.getBloodGroup());
                note.put("Mobile",detailsBook.getMobile());
                note.put("Height",detailsBook.getHeight());
                note.put("Weight",detailsBook.getWeight());
                note.put("Gender",detailsBook.getGender());
                note.put("Birthday",detailsBook.getBday());
                note.put("Mother Name",detailsBook2.getMother());
                note.put("Father Name",detailsBook2.getFather());
                note.put("Mother's Mobile",detailsBook2.getMmob());
                note.put("Father's Mobile",detailsBook2.getFmob());
                note.put("School",sschool);
                note.put("Year",syear);
                note.put("Semester",ssemester);
                note.put("Branch",sbranch);
                note.put("Registration No.",sreg_no);
                note.put("Chronic Disease",schroinc);
                note.put("Room Category",room_c);
                note.put("Seater",room_s);
                note.put("Approved",0);
                note.put("Personal Email",shared_email);
                firebaseFirestore.collection("Students").document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(DetailsOther.this, "Saved", Toast.LENGTH_SHORT).show();
                                Intent APPROVAL=new Intent(DetailsOther.this, Main2Activity.class);
                                startActivity(APPROVAL);

                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("Doc_Name",detailsBook.getFname()+sreg_no);
                                editor.commit();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailsOther.this, "Not success" + e, Toast.LENGTH_SHORT).show();
                            }
                        });


            }

        });


    }
}
