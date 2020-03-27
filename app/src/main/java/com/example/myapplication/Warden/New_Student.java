package com.example.myapplication.Warden;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.New_Student_Adapter;
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
public class New_Student extends Fragment {
ArrayList<String> Name=new ArrayList<>();
ArrayList<String> Branch=new ArrayList<>();
ArrayList<String> Year = new ArrayList<>();
ArrayList<String> Category=new ArrayList<>();
ArrayList<String> Seater=new ArrayList<>();


    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    GridView gridView;
    public New_Student() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_new__student, container, false);
        gridView=v.findViewById(R.id.dashgrid);
        db.collection("Students")
                .whereEqualTo("Approved",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Name.add(document.getString("First Name")+" "+document.getString("Last Name"));
                                Branch.add(document.getString("Branch"));
                                Year.add(document.getString("Year"));
                                Category.add(document.getString("Room Category"));
                                Seater.add(document.getString("Seater"));
                                Log.d("item",Name.toString()+Branch.toString()+Year.toString()+Category.toString()+Seater.toString());
                                /*Log.d("Item",document.getId()+document.getData());
                                Log.d("______","______");*/
                                New_Student_Adapter adapter=new New_Student_Adapter(getActivity().getApplicationContext(),Name,Branch,Year,Category,Seater);
                                gridView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        Log.d("Completed","__");
        return v;
    }

}
