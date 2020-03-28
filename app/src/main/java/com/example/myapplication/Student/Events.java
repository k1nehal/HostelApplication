package com.example.myapplication.Student;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.EventAdapter;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class Events extends Fragment {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> date=new ArrayList<>();
    ArrayList<String> time=new ArrayList<>();
    ArrayList<String> coor=new ArrayList<>();
    ArrayList<String> coorN=new ArrayList<>();

    GridView gridView;

    public Events() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_events, container, false);
        gridView=v.findViewById(R.id.dashgrid);
        db.collection("Events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name.add(document.getString("Event Name"));
                                date.add(document.getString("Event Date"));
                                time.add(document.getString("Event Time"));
                                coor.add(document.getString("Coordinator"));
                                coorN.add(document.getString("Number"));
                                EventAdapter adapter=new EventAdapter(getActivity().getApplicationContext(),name,date,time,coor,coorN);
                                gridView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;

    }
}