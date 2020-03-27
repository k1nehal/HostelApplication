package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class New_Student_Adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> Name;
    private ArrayList<String> Branch;
    private ArrayList<String> Year;
    private ArrayList<String> Category;
    private ArrayList<String> Seater;
    private ArrayList<Room_LIst_Item> roomsList;

    public New_Student_Adapter(Context context, ArrayList<String> name, ArrayList<String> branch, ArrayList<String> year, ArrayList<String> category, ArrayList<String> seater, ArrayList<Room_LIst_Item> roomsList) {
        this.context = context;
        Name = name;
        Branch = branch;
        Year = year;
        Category = category;
        Seater = seater;
        this.roomsList = roomsList;
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
        View v=convertView;


        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_new_student,null);
        }

        TextView textView = convertView.findViewById(R.id.Name);
        TextView textView1 = convertView.findViewById(R.id.Branch);
        TextView textView2 = convertView.findViewById(R.id.Year);
        TextView textView4 = convertView.findViewById(R.id.Seater);
        TextView textView3 = convertView.findViewById(R.id.category);

        textView.setText(Name.get(position));
        textView1.setText(Branch.get(position));
        textView2.setText(Year.get(position));
        textView3.setText(Category.get(position));
        textView4.setText(Seater.get(position));

        ArrayList<String> rooms = new ArrayList<>();
        rooms.add("Select Rooms");

        if (Category.get(position).equals("Fan"))
        {
            if (Seater.get(position).equals("Single Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("Fan"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 1)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
            else if (Seater.get(position).equals("Double Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("Fan"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 2)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
        }
        else if (Category.get(position).equals("Duct"))
        {
            if (Seater.get(position).equals("Single Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("Duct"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 1)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
            else if (Seater.get(position).equals("Double Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("Duct"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 2)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
        }
        else if (Category.get(position).equals("AC"))
        {
            if (Seater.get(position).equals("Single Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("AC"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 1)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
            else if (Seater.get(position).equals("Double Bed"))
            {
                for (Room_LIst_Item item:roomsList)
                {
                    if (item.getCategory().equals("AC"))
                    {
                        if (item.getAlloted() != item.getSeater() && item.getSeater() == 2)
                        {
                            rooms.add(item.getRoom_No());
                        }
                    }
                }
            }
        }

        Spinner spinner = convertView.findViewById(R.id.rooms);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.room_spinner_layout, rooms);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button=convertView.findViewById(R.id.Approve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(v.getContext(),Approve.class);
//                intent.putExtra("Name",Name.get(position));
//                intent.putExtra("Category",Category.get(position));
//                intent.putExtra("Seater",Seater.get(position));
//                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
