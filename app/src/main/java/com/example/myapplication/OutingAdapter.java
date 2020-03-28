package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Warden.OutingRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OutingAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> Name;
    private ArrayList<String> Place;
    private ArrayList<String> Purpose;
    private ArrayList<String> InTime;
    private ArrayList<String> ID;
    private ArrayList<Outing_List_Item> arrayList;
    private View vw;

    FirebaseFirestore db=FirebaseFirestore.getInstance();
    public OutingAdapter(View v,Context context, ArrayList<String> name, ArrayList<String> place, ArrayList<String> purpose, ArrayList<String> inTime, ArrayList<String> id, ArrayList<Outing_List_Item> arrayList) {
        this.vw=v;
        this.context = context;
        Name = name;
        Place = place;
        Purpose = purpose;
        InTime = inTime;
        this.ID=id;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return Name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        View v=convertView;
        vw=convertView;
        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_outing_request,null);
        }

        TextView textView =convertView.findViewById(R.id.Name);
        TextView textView1=convertView.findViewById(R.id.Place);
        TextView textView2=convertView.findViewById(R.id.Purpose);
        TextView textView3=convertView.findViewById(R.id.In_Time);
//        TextView textView4=convertView.findViewById(R.id.CordN);
        textView.setText(Name.get(position));
        textView1.setText(Place.get(position));
        textView2.setText(Purpose.get(position));
        textView3.setText(InTime.get(position));

        Button button,button1;
        button=convertView.findViewById(R.id.Approve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = db.collection("Outings").document(ID.get(position));
                documentReference.update("Status",1);
                Name.remove(position);
                Place.remove(position);
                OutingRequest.UpdateRequests(vw,context,Name,Place,Purpose,InTime,ID,arrayList);
            }
        });

        return convertView;
    }
}
