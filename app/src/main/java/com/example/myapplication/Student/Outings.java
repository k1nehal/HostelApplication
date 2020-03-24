package com.example.myapplication.Student;


import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Outings extends Fragment
{
    private EditText purpose,place,time;
    private Calendar calendar;
    private int currentHour;
    private int currentMinute;
    private String amPm;
    private TimePickerDialog timePickerDialog;
    private CardView cardView;
    private SharedPreferences sharedPreferences;
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    public Outings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_outings, container, false);
        purpose=view.findViewById(R.id.Purpose);
        place=view.findViewById(R.id.Place);
        time=view.findViewById(R.id.In_Time);
        cardView=view.findViewById(R.id.Request);
        sharedPreferences=getActivity().getSharedPreferences("MyHostel", Context.MODE_PRIVATE);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();


            }

        });


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> note=new HashMap<>();
                String Name=sharedPreferences.getString("Stu_FName","")+sharedPreferences.getString("Stu_LName","");

                note.put("Name",Name);
                note.put("Mobile", sharedPreferences.getString("Stu_Mobile", ""));
                note.put("Room",sharedPreferences.getString("Stu_Room",""));
                note.put("Place",place.getText().toString());
                note.put("Purpose",purpose.getText().toString());
                note.put("Status",0);
                note.put("UID", firebaseAuth.getUid());
                note.put("InTime",time.getText().toString());
                note.put("OutTime", FieldValue.serverTimestamp());
                firebaseFirestore.collection("Outings").document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(),"Request Sent",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(DetailsOther.this, "Not success" + e, Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

return  view;

    }



}
