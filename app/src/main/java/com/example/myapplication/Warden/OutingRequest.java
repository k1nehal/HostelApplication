package com.example.myapplication.Warden;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.OutingAdapter;
import com.example.myapplication.Outing_List_Item;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
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
public class OutingRequest extends Fragment {
ArrayList<String> name=new ArrayList<>();
ArrayList<String>  purpose=new ArrayList<>();
ArrayList<String> place=new ArrayList<>();
ArrayList<String> time=new ArrayList<>();
ArrayList<String> id=new ArrayList<>();
ArrayList<Outing_List_Item> arrayList;
GridView gridView;
FirebaseFirestore db=FirebaseFirestore.getInstance();
FirebaseAuth fb=FirebaseAuth.getInstance();


    public OutingRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View v=inflater.inflate(R.layout.fragment_outing_request, container, false);
        gridView=v.findViewById(R.id.dashgrid);
        db.collection("Outings")
                .whereEqualTo("Status",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name.add(document.getString("Name"));
                                place.add(document.getString("Place"));
                                purpose.add(document.getString("Purpose"));
                                time.add(document.getString("InTime"));
                                id.add(document.getId());
                                OutingAdapter adapter=new OutingAdapter(v,getActivity().getApplicationContext(),name,place,purpose,time,id,arrayList);
                                gridView.setAdapter(adapter);
                                Log.d("__",name.toString()+place.toString()+purpose.toString()+time.toString()+id.toString());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;

    }

    public static void UpdateRequests(View v, Context context, ArrayList<String> name, ArrayList<String> purpose, ArrayList<String> place, ArrayList<String> In_Time, ArrayList<String> id,ArrayList<Outing_List_Item> arrayList)
    {
        GridView gV = v.findViewById(R.id.dashgrid);

         OutingAdapter adapter = new OutingAdapter(v, context, name, purpose,place,In_Time,id,arrayList);
        gV.setAdapter(adapter);

    }
}