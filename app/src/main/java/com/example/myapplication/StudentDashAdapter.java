package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class StudentDashAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private String[] itemName;
    private int[] itemImage;

    public StudentDashAdapter(Context c, String[] itemName, int[] itemImage) {
        context = c;
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    @Override
    public int getCount() {
        return itemName.length;
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
            convertView=layoutInflater.inflate(R.layout.dashboarditem,null);
        }

        ImageView imageView=convertView.findViewById(R.id.itemImageI);
        TextView textView=convertView.findViewById(R.id.itemNameI);
        imageView.setImageResource(itemImage[position]);
        textView.setText(itemName[position]);
        return convertView;
    }


}
