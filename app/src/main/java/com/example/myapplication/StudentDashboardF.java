package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class StudentDashboardF extends Fragment {
    GridView gridView;
    String[] itemName={"Complaint","Outings","Leaves","Contacts","Timings","Events"};
    int[]  itemImage={R.drawable.file,R.drawable.gps,R.drawable.pin,R.drawable.avatar,R.drawable.time,R.drawable.calendar};


    public StudentDashboardF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_student_dashboard, container, false);
        gridView=view.findViewById(R.id.dashgrid);
        StudentDashAdapter adapter=new StudentDashAdapter(getActivity().getApplicationContext(),itemName,itemImage);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getActivity().getApplicationContext(), FragmentActivity.class);
                intent.putExtra("Item",itemName[position]);
                startActivity(intent);
               /* String title = itemName[position];
                Fragment fragment = null;
                switch (title) {
                    case "Night Attendance":
                        fragment = new AttendanceFragment();
                        break;
                    case "Complaint":
                        fragment = new Complaint();
                        break;
                    case "Outings":
                        fragment = new Outings();
                        break;
                    default:
                        Toast.makeText(getActivity().getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
                        break;
                }
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();*/
            }

        });
        return view;
    }
}