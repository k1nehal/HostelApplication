package com.example.myapplication.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.R;

import androidx.fragment.app.Fragment;


public class Contact_Warden extends Fragment {
GridView gridView;
String [] names={"Director","Proctor","Chief Warden","Hostel Warden","Transport Officer","Security Supervisor"};
String [] numbers= {"9829000071","9928028145","9001893267","9001893270","9829855509","9001890504","9001890804"};
    public Contact_Warden() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_contact__warden, container, false);
        gridView=view.findViewById(R.id.dashgrid);
        ContactAdapter adapter=new ContactAdapter(getActivity().getApplicationContext(),names,numbers);
        gridView.setAdapter(adapter);
        return  view;
    }
}
