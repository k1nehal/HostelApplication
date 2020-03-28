package com.example.myapplication.Warden;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

import androidx.fragment.app.Fragment;


public class Records extends Fragment {


    public Records() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         View v =inflater.inflate(R.layout.fragment_records, container, false);
         return v;
    }
}
