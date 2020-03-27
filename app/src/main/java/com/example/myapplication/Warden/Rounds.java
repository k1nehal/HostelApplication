package com.example.myapplication.Warden;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Rounds extends Fragment {
    Spinner spinner;
    EditText editText;
    CardView c;
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    public Rounds() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_rounds, container, false);
        spinner=view.findViewById(R.id.Round);
        editText=view.findViewById(R.id.Comments);
        c=view.findViewById(R.id.Submit);
        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(getContext(),R.array.Round,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        Date cd = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(cd);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String round= spinner.getSelectedItem().toString();
                String comments=editText.getText().toString();
                Map<String,Object> note=new HashMap<>();
                note.put("Round",round);
                note.put("Comments", comments);
                note.put("Date Time",formattedDate);

                firebaseFirestore.collection("Round Records").document().set(note)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(),"Complaint Saved",Toast.LENGTH_SHORT).show();

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



        return view;
    }
}
