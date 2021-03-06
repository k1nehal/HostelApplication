package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

public class StudentDashboardF extends Fragment {
    GridView gridView;
    String[] itemName={"Complaint","Outings","Leaves","Contacts","Timings","Events"};
    int[]  itemImage={R.drawable.com,R.drawable.out,R.drawable.leave,R.drawable.cn,R.drawable.tt,R.drawable.cal};


    public StudentDashboardF() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_student_dashboard, container, false);
        gridView=view.findViewById(R.id.dashgrid);
        StudentDashAdapter adapter=new StudentDashAdapter(getActivity().getApplicationContext(),itemName,itemImage);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity().getApplicationContext(), FragmentActivity.class);
                intent.putExtra("Item", itemName[position]);
                startActivity(intent);

            }
        });
        return view;
    }
}