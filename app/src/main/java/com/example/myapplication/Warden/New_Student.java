package com.example.myapplication.Warden;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.New_Student_Adapter;
import com.example.myapplication.R;
import com.example.myapplication.Room_LIst_Item;
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

    private ArrayList<String> Name=new ArrayList<>();
    private ArrayList<String> Branch=new ArrayList<>();
    private ArrayList<String> Year = new ArrayList<>();
    private ArrayList<String> Category=new ArrayList<>();
    private ArrayList<String> Seater=new ArrayList<>();
    private ArrayList<String> docId = new ArrayList<>();
    public static ArrayList<Room_LIst_Item> roomsList;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();
    private GridView gridView;

    public New_Student() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_new__student, container, false);
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
                                docId.add(document.getId());
                                New_Student_Adapter adapter=new New_Student_Adapter(v,getActivity().getApplicationContext(),Name,Branch,Year,Category,Seater, docId,roomsList);
                                gridView.setAdapter(adapter);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;
    }

    public static void setGridViewAdapter(View v, Context context, ArrayList<String> name, ArrayList<String> branch, ArrayList<String> year, ArrayList<String> category, ArrayList<String> seater, ArrayList<String> docId, ArrayList<Room_LIst_Item> roomsList)
    {
        GridView gV = v.findViewById(R.id.dashgrid);

        New_Student_Adapter adapter = new New_Student_Adapter(v, context, name, branch, year, category, seater, docId, roomsList);
        gV.setAdapter(adapter);

    }

}
