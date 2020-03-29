package com.example.myapplication.Student;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Complaint extends Fragment {
    SharedPreferences sharedPreferences;
    String Name, room, Category, Place =null, Comments;

    Spinner category_s,sub_cat;
    EditText EComments;
    CardView Submit;
    Calendar calender= Calendar.getInstance();
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    public Complaint() {

        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_complaint, container, false);
        category_s= view.findViewById(R.id.cat_spinner);
        sub_cat=view.findViewById(R.id.sub_cat_spinner);
        EComments=view.findViewById(R.id.complain);
        Submit=view.findViewById(R.id.Submit);
        final long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM, dd YYYY");
        final String dateString = sdf.format(date);
        sharedPreferences=getActivity().getSharedPreferences("MyHostel", Context.MODE_PRIVATE);
        final String shared_email=sharedPreferences.getString("Email", "");
        ArrayAdapter<CharSequence> arrayAdapter= ArrayAdapter.createFromResource(getContext(),R.array.complaint_category,R.layout.support_simple_spinner_dropdown_item);

        category_s.setAdapter(arrayAdapter);
        category_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String test= (String) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(),test+" ",Toast.LENGTH_SHORT).show();
                ArrayAdapter<CharSequence> arrayAdapter1;
                switch(position)
                {
                    case 1:
                        arrayAdapter1=ArrayAdapter.createFromResource(getContext(),R.array.cat_room,R.layout.support_simple_spinner_dropdown_item);
                        sub_cat.setEnabled(true);
                        sub_cat.setAdapter(arrayAdapter1);

                        break;
                    case 2:
                        sub_cat.setEnabled(true);
                        arrayAdapter1=ArrayAdapter.createFromResource(getContext(),R.array.cat_floor,R.layout.support_simple_spinner_dropdown_item);
                        sub_cat.setAdapter(arrayAdapter1);
                        break;
                    default:
                        sub_cat.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category=category_s.getSelectedItem().toString();
//                Place=sub_cat.getSelectedItem().toString();
                Comments=EComments.getText().toString();
                Map<String,Object> note=new HashMap<>();
                Name=sharedPreferences.getString("Stu_FName","")+" "+sharedPreferences.getString("Stu_LName","");
                Log.d("Msg","_______________");
                Log.d("NAme",Name);
                note.put("Name",Name);
                note.put("Mobile", sharedPreferences.getString("Stu_Mobile", ""));
                note.put("Room",sharedPreferences.getString("Room",""));
                note.put("Category",category_s.getSelectedItem().toString());
                note.put("Date",dateString);
                if (sub_cat.isEnabled()==true) {
                    note.put("Place",sub_cat.getSelectedItem().toString());
                }
                else
                {
                    note.put("Place",null);
                }
                note.put("Comment",EComments.getText().toString());
                note.put("Status",0);
                note.put("UID", firebaseAuth.getUid());

                firebaseFirestore.collection("Complaints").document().set(note)
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
