package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
Spinner school,year,semester;
EditText branch,reg_no,roomchoice;
CardView submit;
RadioGroup chronicg;
//RadioButton chronicb;
ProgressBar progressBar;
TextView textView;
String sschool,syear,ssemester,sbranch,sreg_no,schroinc,iroom;
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
        roomchoice=findViewById(R.id.Room);

        sharedPreferences=getSharedPreferences("MyHostel",MODE_PRIVATE);
        final String shared_email=sharedPreferences.getString("Email", "");


        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem=parent.getSelectedItem().toString();
                if(selectedItem.equals("I"))
                {
                    roomchoice.setEnabled(false);
                }
                else
                {
                    roomchoice.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Year, R.layout.support_simple_spinner_dropdown_item);
        year.setAdapter(arrayAdapter);
        ArrayAdapter<CharSequence> arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.School, R.layout.support_simple_spinner_dropdown_item);
        school.setAdapter(arrayAdapter1);
        ArrayAdapter<CharSequence> arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.Sem, R.layout.support_simple_spinner_dropdown_item);
        semester.setAdapter(arrayAdapter2);

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
                iroom=roomchoice.getText().toString();

                schroinc =((RadioButton)findViewById(chronicg.getCheckedRadioButtonId())).getText().toString();

                Map<String,Object> note=new HashMap<>();
                note.put("FirstName",detailsBook.getFname());
                note.put("LastName",detailsBook.getLname());
                note.put("PEmail",detailsBook.getPemail());
                note.put("BloodGroup",detailsBook.getBloodGroup());
                note.put("Mobile",detailsBook.getMobile());
                note.put("Height",detailsBook.getHeight());
                note.put("Weight",detailsBook.getWeight());
                note.put("Gender",detailsBook.getGender());
                note.put("Bday",detailsBook.getBday());

                note.put("MotherName",detailsBook2.getMother());
                note.put("FatherName",detailsBook2.getFather());
                note.put("MMobile",detailsBook2.getMmob());
                note.put("FMobile",detailsBook2.getFmob());

                note.put("School",sschool);
                note.put("Year",syear);
                note.put("Semester",ssemester);
                note.put("Branch",sbranch);
                note.put("Reg_no",sreg_no);
                note.put("Chronic",schroinc);
                note.put("Room Choice",iroom);
                note.put("approved",0);
//                note.put("Personal_Email",shared_email);

                firebaseFirestore.collection("Students").document(shared_email).set(note)
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
