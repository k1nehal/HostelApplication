package com.example.myapplication;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class Leaves extends Fragment {
EditText r_time,r_date,reason,p_mobile,address;
Spinner spinners;
CardView cardView;

    public Leaves() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_outings, container, false);
        return  view;
    }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_leaves, container, false);
//    }

}
