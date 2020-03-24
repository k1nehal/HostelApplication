package com.example.myapplication.Details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DetailsParent extends AppCompatActivity {
    EditText mother_n,father_n,mother_mob,father_mob;
    CardView Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_details);
        mother_n=findViewById(R.id.M_Name);
        father_n=findViewById(R.id.F_Name);
        mother_mob=findViewById(R.id.M_Mobile);
        father_mob=findViewById(R.id.F_Mobile);
        Next=findViewById(R.id.Next);

        final Intent intent=getIntent();
        final DetailsBook detailsBook=intent.getParcelableExtra("Book");

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mother,father;
               long mmobile,fmobile;
                mother=mother_n.getText().toString();
                father=father_n.getText().toString();
                mmobile=Long.parseLong(mother_mob.getText().toString());
                fmobile=Long.parseLong(father_mob.getText().toString());

                DetailsBook2 detailsBook2=new DetailsBook2(mother,father,mmobile,fmobile);
                Intent intent1=new Intent(DetailsParent.this,DetailsOther.class);
                intent1.putExtra("Book",detailsBook);
                intent1.putExtra("Book1",detailsBook2);
                startActivity(intent1);
            }
        });
    }
}
