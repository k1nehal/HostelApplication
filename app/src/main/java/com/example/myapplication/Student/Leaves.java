package com.example.myapplication.Student;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class Leaves extends Fragment {
    SharedPreferences sharedPreferences;
    EditText r_time,r_date,reason,address;
    Spinner spinners;
    CardView cardView;
    private Calendar mycalendar=Calendar.getInstance();
    private int currentHour;
    private int currentMinute;
    private String amPm;
    private TimePickerDialog timePickerDialog;
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();


    public Leaves() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_leaves, container, false);
        r_date=view.findViewById(R.id.R_Date);
        r_time=view.findViewById(R.id.R_Time);
        reason=view.findViewById(R.id.Reason);
        address=view.findViewById(R.id.Address);
        spinners=view.findViewById(R.id.Mobile);
        cardView=view.findViewById(R.id.Approval);
        sharedPreferences= getActivity().getSharedPreferences("MyHostel",MODE_PRIVATE);
        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add(sharedPreferences.getString("M_Mobile",""));
        arrayList.add(sharedPreferences.getString("F_Mobile",""));
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_spinner_item,arrayList);
        spinners.setAdapter(arrayAdapter);

        r_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, mycalendar
                        .get(Calendar.YEAR), mycalendar.get(Calendar.MONTH),
                        mycalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        r_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentHour = mycalendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = mycalendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        r_time.setText(String.format("%02d:%02d", hourOfDay, minutes) + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> note=new HashMap<>();
                String Name=sharedPreferences.getString("Stu_FName","")+" "+sharedPreferences.getString("Stu_LName","");

                note.put("Name",Name);
                note.put("UID",firebaseAuth.getUid());
                note.put("Mobile",sharedPreferences.getString("Stu_Mobile",""));
                note.put("Return Date",r_date.getText().toString());
                note.put("Return Time",r_time.getText().toString());
                note.put("Address",address.getText().toString());
                note.put("Reason",reason.getText().toString());
                note.put("Mobile",spinners.getSelectedItem().toString());
                note.put("OutDateTime",FieldValue.serverTimestamp());
                note.put("Status",0);

                firebaseFirestore.collection("Leaves").document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(),"Request Sent",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });
        return  view;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            mycalendar.set(Calendar.YEAR, year);
            mycalendar.set(Calendar.MONTH, monthOfYear);
            mycalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();

        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        r_date.setText(sdf.format(mycalendar.getTime()));
    }
}
