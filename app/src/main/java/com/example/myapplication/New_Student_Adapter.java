package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Warden.New_Student;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class New_Student_Adapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> Name;
    private ArrayList<String> Branch;
    private ArrayList<String> Year;
    private ArrayList<String> Category;
    private ArrayList<String> Seater;
    private ArrayList<String> docId;
    private ArrayList<Room_LIst_Item> roomsList;
    private View vw;

    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    public New_Student_Adapter(View v, Context context, ArrayList<String> name, ArrayList<String> branch, ArrayList<String> year, ArrayList<String> category, ArrayList<String> seater, ArrayList<String> docId, ArrayList<Room_LIst_Item> roomsList) {
        vw = v;
        this.context = context;
        Name = name;
        Branch = branch;
        Year = year;
        Category = category;
        Seater = seater;
        this.docId = docId;
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

        final ArrayList<String> rooms = new ArrayList<>();
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

        final Spinner spinner = convertView.findViewById(R.id.rooms);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.room_spinner_layout, rooms);
        spinner.setAdapter(adapter);

        final int[] spinnerItem = new int[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0)
                {
                    spinnerItem[0] = position;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button button=convertView.findViewById(R.id.Approve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a;
                //rooms.get(spinnerItem[0]);
                if (spinnerItem[0] != 0)
                {
                    Name.remove(position);
                    Branch.remove(position);
                    Year.remove(position);
                    Category.remove(position);
                    Seater.remove(position);
                    Room_LIst_Item room_lIst_item = roomsList.get(spinnerItem[0]-1);
                    room_lIst_item.setAlloted(room_lIst_item.getAlloted()+1);
                    //roomsList.remove(spinnerItem[0]);
                    //roomsList.add(spinnerItem[0], room_lIst_item);
                    roomsList.set(spinnerItem[0]-1, room_lIst_item);

                    db.collection("Rooms")
                            .document(spinner.getSelectedItem().toString())
                            .update("Alloted", room_lIst_item.getAlloted())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Room Detail", "Room Detail updated");
                                }
                            });

                    db.collection("Students")
                            .document(docId.get(position))
                            .update("Approved", 1)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("Student Detail", "Student Approved Successful");
                                    New_Student.setGridViewAdapter(vw,context,Name, Branch, Year, Category, Seater, docId,roomsList);
                                }
                            });

                }
                else
                {
                    Toast.makeText(context, "Please select Room No.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
    }
}
