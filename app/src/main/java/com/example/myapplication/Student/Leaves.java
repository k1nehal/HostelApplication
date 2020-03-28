package com.example.myapplication.Student;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class Leaves extends Fragment {
    SharedPreferences sharedPreferences;
    EditText r_time,r_date,reason,address;
    Spinner spinners;
    CardView cardView;
    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();


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
        return  view;
    }
}
