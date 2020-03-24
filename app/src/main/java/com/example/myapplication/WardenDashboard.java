package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class WardenDashboard extends Activity {
    GridView gridView;
    String[] itemName={"New Students","Round_Records","Leave Request","Outing Request","Complaint Request","New Event"};
    int[]  itemImage={R.drawable.file,R.drawable.gps,R.drawable.pin,R.drawable.avatar,R.drawable.time,R.drawable.calendar};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_dashboard);
        gridView=findViewById(R.id.dashgrid);
        StudentDashAdapter adapter=new StudentDashAdapter(getApplicationContext(),itemName,itemImage);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(WardenDashboard.this, FragmentActivity.class);
                intent.putExtra("Item",itemName[position]);
                startActivity(intent);

            }
        });


    }

}
