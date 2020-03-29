package com.example.myapplication.Warden;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.LeaveAdapter;
import com.example.myapplication.Leave_List_Item;
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
public class LeaveRequests extends Fragment {
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String>  return_d=new ArrayList<>();
    ArrayList<String> return_t=new ArrayList<>();
    ArrayList<String> reason=new ArrayList<>();
    ArrayList<String> address=new ArrayList<>();
    ArrayList<String> mobile=new ArrayList<>();
    ArrayList<String> id=new ArrayList<>();
    ArrayList<Leave_List_Item> arrayList;
    GridView gridView;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    FirebaseAuth fb=FirebaseAuth.getInstance();


    public LeaveRequests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View v=inflater.inflate(R.layout.fragment_leave_requests, container, false);
        gridView=v.findViewById(R.id.dashgrid);
        db.collection("Leaves")
                .whereEqualTo("Status",0)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                name.add(document.getString("Name"));
                                return_d.add(document.getString("Return Date"));
                                return_t.add(document.getString("Return Time"));
                                reason.add(document.getString("Reason"));
                                address.add(document.getString("Address"));
                                mobile.add(document.getString("Mobile"));
                                id.add(document.getId());
                                LeaveAdapter adapter=new LeaveAdapter(v,getActivity().getApplicationContext(),name,return_d,return_t,reason,address,mobile,id,arrayList);
                                gridView.setAdapter(adapter);
//                                Log.d("__",name.toString()+place.toString()+purpose.toString()+time.toString()+id.toString());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;
    }

    public static void UpdateRequests(View view, Context context, ArrayList<String> name, ArrayList<String> return_d, ArrayList<String> return_t, ArrayList<String> reason, ArrayList<String> address, ArrayList<String> mobile, ArrayList<String> id,ArrayList<Leave_List_Item> arrayList)
    {
        GridView gV = view.findViewById(R.id.dashgrid);
        LeaveAdapter adapter=new LeaveAdapter(view,context,name,return_d,return_t,reason,address,mobile,id,arrayList);
        gV.setAdapter(adapter);
    }

}
