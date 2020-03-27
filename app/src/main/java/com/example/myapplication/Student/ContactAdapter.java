package com.example.myapplication.Student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

class ContactAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private String[] Name;
    private String[] Number;

    public ContactAdapter(Context context, String[] name, String[] number) {
        this.context = context;
        Name = name;
        Number = number;
    }


    public int getCount() {
        return Name.length;
    }


    public Object getItem(int position) {
        return null;
    }


    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View v=convertView;


        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_contact,null);
        }

        TextView textView =convertView.findViewById(R.id.Name);
        TextView textView1=convertView.findViewById(R.id.Number);
        textView.setText(Name[position]);
        textView1.setText(Number[position]);

        return convertView;
    }

}
