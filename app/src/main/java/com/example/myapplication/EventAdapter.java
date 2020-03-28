package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> Name;
    private ArrayList<String> Date;
    private ArrayList<String> Time;
    private ArrayList<String> Coor;
    private ArrayList<String> CoorN;

    public EventAdapter(Context context, ArrayList<String> name, ArrayList<String> date, ArrayList<String> time, ArrayList<String> coor, ArrayList<String> coorN) {
        this.context = context;
        Name = name;
        Date = date;
        Time = time;
        Coor = coor;
        CoorN = coorN;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=convertView;


        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_event,null);
        }

        TextView textView =convertView.findViewById(R.id.Name);
        TextView textView1=convertView.findViewById(R.id.Date);
        TextView textView2=convertView.findViewById(R.id.Time);
        TextView textView3=convertView.findViewById(R.id.Cord);
        TextView textView4=convertView.findViewById(R.id.CordN);
        textView.setText(Name.get(position));
        textView1.setText(Date.get(position));
        textView2.setText(Time.get(position));
        textView3.setText(Coor.get(position));
        textView4.setText(CoorN.get(position));

        return convertView;
    }
}
