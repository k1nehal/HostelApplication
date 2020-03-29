package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.Warden.LeaveRequests;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LeaveAdapter extends BaseAdapter {
    private View vw;
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> Name;
    private ArrayList<String> return_d;
    private ArrayList<String> return_t;
    private ArrayList<String> reason;
    private ArrayList<String> address;
    private ArrayList<String> mobile;
    private ArrayList<String> id;
    private ArrayList<Leave_List_Item> arrayList;
//    private View vw;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    public LeaveAdapter(View vw, Context context, ArrayList<String> name, ArrayList<String> return_d, ArrayList<String> return_t, ArrayList<String> reason, ArrayList<String> address, ArrayList<String> mobile, ArrayList<String> id, ArrayList<Leave_List_Item> arrayList) {
        this.vw = vw;
        this.context = context;
        Name = name;
        this.return_d = return_d;
        this.return_t = return_t;
        this.reason = reason;
        this.address = address;
        this.mobile = mobile;
        this.id = id;
        this.arrayList = arrayList;
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
        if(layoutInflater==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(R.layout.item_leave,null);
        }

        TextView textView =convertView.findViewById(R.id.Name);
        TextView textView1=convertView.findViewById(R.id.date);
        TextView textView2=convertView.findViewById(R.id.time);
        TextView textView3=convertView.findViewById(R.id.address);
        TextView textView4=convertView.findViewById(R.id.mobile);

        textView.setText(Name.get(position));
        textView1.setText(return_d.get(position));
        textView2.setText(return_t.get(position));
        textView3.setText(address.get(position));
        textView4.setText(mobile.get(position));

        Button button,button1;
        button=convertView.findViewById(R.id.Approve);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference = db.collection("Leaves").document(id.get(position));
                documentReference.update("Status",1);
                Name.remove(position);
                return_d.remove(position);
                return_t.remove(position);
                address.remove(position);
                mobile.remove(position);
                id.remove(position);
                LeaveRequests.UpdateRequests(vw,context,Name,return_d,return_t,reason,address,mobile,id,arrayList);

                            }
        });

        return convertView;
    }
    }

