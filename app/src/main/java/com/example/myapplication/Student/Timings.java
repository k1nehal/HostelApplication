package com.example.myapplication.Student;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.R;
import com.example.myapplication.TimingAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class Timings extends Fragment {
    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    ArrayList<String> timings=new ArrayList<>();
    ArrayList<String> duration=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    GridView gridView;
    public Timings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_timings, container, false);
//        final TextView textView=v.findViewById(R.id.text);
        gridView=v.findViewById(R.id.dashgrid);
        db.collection("Timings")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("Item",document.getId()+document.getData());
//                                textView.setText(document.getId() + document.getData());
//                                String MTinings=document.getString("Time");
                                timings.add(document.getString("Time"));
                                duration.add(document.getString("Duration"));
                                name.add(document.getString("Name"));
                                TimingAdapter adapter=new TimingAdapter(getActivity().getApplicationContext(),name,timings,duration);
                                gridView.setAdapter(adapter);

//                                Log.d("_________"," ____");
//                                Log.d("List: ",timings.toString()+duration.toString());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;

    }
}
